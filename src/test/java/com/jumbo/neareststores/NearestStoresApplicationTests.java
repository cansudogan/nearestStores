package com.jumbo.neareststores;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(initializers = TestInitializer.class)
@SpringBootTest
@EnableAutoConfiguration
public
class NearestStoresApplicationTests {

    @Test
    void contextLoads() {
    }

}
