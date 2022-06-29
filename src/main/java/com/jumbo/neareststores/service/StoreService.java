package com.jumbo.neareststores.service;

import com.jumbo.neareststores.entity.Store;
import com.jumbo.neareststores.model.Stores.StoreDto;
import com.jumbo.neareststores.model.request.SearchFilterRequest;
import com.jumbo.neareststores.model.response.SearchFilterResponse;
import com.jumbo.neareststores.repository.Custom.CustomStoreRepository;
import com.jumbo.neareststores.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final CustomStoreRepository customStoreRepository;

    public Page<Store> getNearestStores(Double latitude, Double longitude, Integer size, Integer page) {
        return customStoreRepository.getNearestStores(new GeoJsonPoint(latitude, longitude), PageRequest.of(page, size));
    }

    public SearchFilterResponse getNearestStoresBySearchFilter(Point point, Pageable page, SearchFilterRequest searchFilterRequest) {
        PageRequest pageWithNext = PageRequest.of(page.getPageNumber(), page.getPageSize() + 1);
        List<Store> data = customStoreRepository.getNearestStoresBySearchFilter(point, pageWithNext, searchFilterRequest);
        var hasNext = data.size() == pageWithNext.getPageSize();

        var response = new SearchFilterResponse();
        if (data.isEmpty()) {
            response.setStores(emptyList());
        } else {
            data.remove(data.size() - 1);
            response.setStores(data);
        }

        response.setHasNext(hasNext);

        return response;
    }

    @Transactional
    public void saveInitialStores(List<StoreDto> stores) {
        storeRepository.saveAll(stores.stream()
                .map(Store::of)
                .collect(Collectors.toList()));
    }
}
