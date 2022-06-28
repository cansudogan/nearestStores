package com.jumbo.neareststores.service;

import com.jumbo.neareststores.entity.Store;
import com.jumbo.neareststores.model.Stores.StoreDto;
import com.jumbo.neareststores.model.request.SearchFilterRequest;
import com.jumbo.neareststores.repository.Custom.CustomStoreRepository;
import com.jumbo.neareststores.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final CustomStoreRepository customStoreRepository;
    public Page<Store> getNearestStores(Double latitude, Double longitude, Integer size, Integer page) {
        return customStoreRepository.getNearestStores(new GeoJsonPoint(latitude, longitude), PageRequest.of(page, size));
    }

    public Page<Store> getNearestStoresBySearchFilter(Point point, PageRequest page, SearchFilterRequest searchFilterRequest) {
        return customStoreRepository.getNearestStoresBySearchFilter(point, page, searchFilterRequest);
    }

    @Transactional
    public void saveInitialStores(List<StoreDto> stores){
        storeRepository.saveAll(stores.stream()
                .map(Store::of)
                .collect(Collectors.toList()));
    }
}
