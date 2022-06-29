package com.jumbo.neareststores.configuration;

import com.jumbo.neareststores.TestInitializer;
import com.jumbo.neareststores.model.Stores;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ContextConfiguration(initializers = TestInitializer.class)
@SpringBootTest
class StoreInitializerTest {
    @Autowired
    InitialData initialData;

    @Test
    @SneakyThrows
    void shouldThrowExceptionWhenInitialNotPresent() {
        FileNotFoundException notFoundException = assertThrows(FileNotFoundException.class,
                () -> initialData.initialStores("classpath:/no-stores.json"));
        assertNotNull(notFoundException);
    }

    @Test
    @SneakyThrows
    void shouldReturnNullWhenInitialDataIsNull() {
        Stores list = initialData.initialStores("classpath:/empty-stores.json");
        assertNull(list.getStores());
    }

    @Test
    @SneakyThrows
    void shouldReturnValidInitialData() {
        List<Stores.StoreDto> list = initialData.initialStores("classpath:/stores.json").getStores();

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals("Kerkstraat", list.get(0).getStreet());

    }
}