package com.jumbo.neareststores.repository;

import com.jumbo.neareststores.entity.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StoreRepository extends MongoRepository<Store, String> {
    List<Store> findByCollectionPointAndLocationNear(Boolean collectionPoint, Point location, Pageable page);

    Long countByCollectionPointAndLocationNear(Boolean collectionPoint, GeoJsonPoint location);
}
