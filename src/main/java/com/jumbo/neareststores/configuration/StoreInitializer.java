package com.jumbo.neareststores.configuration;

import com.jumbo.neareststores.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StoreInitializer {
    private final InitialData initialData;
    private final StoreService storeService;
    private static final String RESOURCE_LOCATION = "classpath:/stores.json";


    @EventListener(ContextRefreshedEvent.class)
    public void init(ContextRefreshedEvent event) {
        log.info("Calling storeService to load stores info");
        storeService.saveInitialStores(initialData.initialStores(RESOURCE_LOCATION).getStores());
        log.info("Stores info saved successfully into DB");
    }
}
