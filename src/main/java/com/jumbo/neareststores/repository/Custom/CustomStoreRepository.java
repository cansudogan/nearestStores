package com.jumbo.neareststores.repository.Custom;

import com.jumbo.neareststores.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomStoreRepository {
    private final MongoTemplate mongoTemplate;
    private final MongoOperations mongoOperations;

    public Page<Store> getNearestStores(Point point, Pageable pageable){
        Query query = new Query().with(pageable);

        Criteria criteria = Criteria.where("location").near(point);
        query.addCriteria(criteria);
        List<Store> stores = mongoTemplate.find(query, Store.class);

        return PageableExecutionUtils.getPage(
                stores,
                pageable,
                () -> mongoOperations.count(new Query().limit(-1).skip(-1), "stores"));
    }

}
