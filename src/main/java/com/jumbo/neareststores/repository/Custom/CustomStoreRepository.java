package com.jumbo.neareststores.repository.Custom;

import com.jumbo.neareststores.entity.Store;
import com.jumbo.neareststores.model.request.SearchFilterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static org.apache.tomcat.util.http.FastHttpDateFormat.getCurrentDate;

@Repository
@RequiredArgsConstructor
public class CustomStoreRepository {
    private final MongoTemplate mongoTemplate;
    private final MongoOperations mongoOperations;

    public Page<Store> getNearestStores(Point point, Pageable pageable){
        Query query = new Query().with(pageable);

        Criteria criteria = Criteria.where(Store.Fields.location).near(point);
        query.addCriteria(criteria);
        List<Store> stores = mongoTemplate.find(query, Store.class);

        return PageableExecutionUtils.getPage(
                stores,
                pageable,
                () -> mongoTemplate.count(new Query().skip(0).limit(0), Store.class));
    }

    public Page<Store> getNearestStoresBySearchFilter(Point point, Pageable pageable, SearchFilterRequest searchFilterRequest){
       List<Criteria> criteria = new ArrayList<>();
        criteria.add(Criteria.where(Store.Fields.location).near(point));

        if(nonNull(searchFilterRequest.getCollectionPoint())){
           criteria.add(Criteria.where(Store.Fields.collectionPoint).is(true));
       }

        if (nonNull(searchFilterRequest.getTodayOpen()) && nonNull(searchFilterRequest.getTodayClose())){
            criteria.add(Criteria.where(Store.Fields.todayOpen).gte(LocalTime.now().getHour()));
            criteria.add(Criteria.where(Store.Fields.todayClose).lte(LocalTime.now().getHour()));
        }

        Query dynamicQuery = new Query().with(pageable);
       dynamicQuery.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));

        return PageableExecutionUtils.getPage(mongoTemplate.find(dynamicQuery, Store.class),
               pageable,
               () -> mongoTemplate.count(dynamicQuery.skip(0).limit(0), Store.class)
       );
    }
}
