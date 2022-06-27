package com.jumbo.neareststores.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.neareststores.model.Stores;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitialData {
    private final ObjectMapper mapper;
    private final ResourceLoader resourceLoader;

    @SneakyThrows
    public Stores initialStores(String resourceLocation){
        return mapper.readValue(resourceLoader.getResource(resourceLocation).getInputStream(), Stores.class);
    }
}
