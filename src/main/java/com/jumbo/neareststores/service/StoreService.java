package com.jumbo.neareststores.service;

import com.jumbo.neareststores.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    public List<Store> getNearestStores() {
        return new ArrayList<Store>();
    }
}
