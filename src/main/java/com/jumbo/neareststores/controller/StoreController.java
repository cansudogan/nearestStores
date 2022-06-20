package com.jumbo.neareststores.controller;

import com.jumbo.neareststores.entity.Store;
import com.jumbo.neareststores.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@Tag(name = "stores", description = "Endpoints about Jumbo stores")
public class StoreController {
    private final StoreService storeService;

    @GetMapping()
    @Operation(summary = "Find nearest stores")
    public List<Store> getNearestStores() {
        return storeService.getNearestStores();
    }
}
