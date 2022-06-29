package com.jumbo.neareststores.controller;

import com.jumbo.neareststores.entity.Store;
import com.jumbo.neareststores.model.request.SearchFilterRequest;
import com.jumbo.neareststores.model.response.SearchFilterResponse;
import com.jumbo.neareststores.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@Tag(name = "stores", description = "Endpoints about Jumbo stores")
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/stores")
    @Operation(summary = "Find nearest stores")
    public Page<Store> getNearestStores(@RequestParam(name = "lat") Double latitude,
                                        @RequestParam(name = "lon") Double longitude,
                                        @RequestParam(name = "numberOfStores") Optional<Integer> numberOfStores,
                                        @RequestParam(name = "pageNumber") Optional<Integer> page) {
        return storeService.getNearestStores(latitude, longitude, numberOfStores.orElseGet(() -> 5), page.orElseGet(() -> 0));
    }

    @GetMapping("/search/stores")
    @Operation(summary = "Get nearest stores by search filter")
    public SearchFilterResponse getNearestStoresBySearchFilter(@Valid SearchFilterRequest searchFilterRequest) {
        return storeService.getNearestStoresBySearchFilter(searchFilterRequest.createPoint(), searchFilterRequest.createPage(), searchFilterRequest);
    }
}
