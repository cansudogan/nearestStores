package com.jumbo.neareststores.repository.Custom;

import com.jumbo.neareststores.entity.Store;
import com.jumbo.neareststores.model.request.SearchFilterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.BooleanUtils.isTrue;

@Repository
@RequiredArgsConstructor
public class CustomStoreRepository {
    private final static String closed = "Gesloten";
    private final MongoTemplate mongoTemplate;

    public Page<Store> getNearestStores(Point point, Pageable pageable) {
        Query query = new Query().with(pageable);

        Criteria criteria = Criteria.where(Store.Fields.location).near(point);
        query.addCriteria(criteria);
        List<Store> stores = mongoTemplate.find(query, Store.class);

        return PageableExecutionUtils.getPage(
                stores,
                pageable,
                () -> mongoTemplate.count(new Query().skip(0).limit(0), Store.class));
    }

    public List<Store> getNearestStoresBySearchFilter(Point point, Pageable pageable, SearchFilterRequest searchFilterRequest) {
        List<Criteria> criteria = new ArrayList<>();
        criteria.add(Criteria.where(Store.Fields.location).near(point));

        if (nonNull(searchFilterRequest.getCollectionPoint())) {
            criteria.add(Criteria.where(Store.Fields.collectionPoint).is(searchFilterRequest.getCollectionPoint()));
        }

        if (nonNull(searchFilterRequest.getClosed()) && isTrue(searchFilterRequest.getClosed())) {
            criteria.add(Criteria.where(Store.Fields.todayOpen).is(closed));
            criteria.add(Criteria.where(Store.Fields.todayClose).is(closed));
        }

        Query dynamicQuery = new Query().with(pageable);
        dynamicQuery.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));

        return mongoTemplate.find(dynamicQuery.with(pageable), Store.class);
    }
}
