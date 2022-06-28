package com.jumbo.neareststores.service;

import com.jumbo.neareststores.TestInitializer;
import com.jumbo.neareststores.entity.Store;
import com.jumbo.neareststores.repository.StoreRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@ContextConfiguration(initializers = TestInitializer.class)
@SpringBootTest
class StoreServiceTest {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreService storeService;

    @BeforeEach
    @SneakyThrows
    void setUp(){
        storeRepository.saveAll(
                List.of(Store.builder().addressName("trial1").location(new GeoJsonPoint(1.10, 1.10)).build(),
                        Store.builder().addressName("trial2").location(new GeoJsonPoint(1.10, 1.10)).build(),
                        Store.builder().addressName("trial3").location(new GeoJsonPoint(2.10, 2.10)).build(),
                        Store.builder().addressName("trial4").location(new GeoJsonPoint(2.20, 2.20)).build(),
                        Store.builder().addressName("trial5").location(new GeoJsonPoint(3.10, 3.10)).build(),
                        Store.builder().addressName("trial6").location(new GeoJsonPoint(3.20, 3.20)).build(),
                        Store.builder().addressName("trial7").location(new GeoJsonPoint(4.10, 4.10)).build(),
                        Store.builder().addressName("trial8").location(new GeoJsonPoint(4.20, 4.20)).build(),
                        Store.builder().addressName("trial9").location(new GeoJsonPoint(5.10, 5.10)).build(),
                        Store.builder().addressName("trial10").location(new GeoJsonPoint(5.20, 5.20)).build()));
    }

    @AfterEach
    public void cleanDB() {
        storeRepository.deleteAll();
    }

    @Nested
    @DisplayName("Find nearest stores")
    class FindNearestStores {
        @Test
        void shouldFindNearestStores(){
            Page<Store> result = storeService.getNearestStores(3.0, 3.0, 5,0);
            Assertions.assertEquals(3, result.getTotalPages());
            Assertions.assertEquals(11, result.getTotalElements());
            Assertions.assertEquals(5, result.getContent().size());
            Assertions.assertEquals(3.10, result.getContent().get(0).getLocation().getX(), 0.0005);
            Assertions.assertEquals(3.10, result.getContent().get(0).getLocation().getY(), 0.0005);
            Assertions.assertEquals(3.20, result.getContent().get(1).getLocation().getX(), 0.0005);
            Assertions.assertEquals(3.20, result.getContent().get(1).getLocation().getY(), 0.0005);
        }
    }


}