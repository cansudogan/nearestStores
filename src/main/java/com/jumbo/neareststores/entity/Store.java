package com.jumbo.neareststores.entity;

import com.jumbo.neareststores.model.Stores.StoreDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "stores")
public class Store {
    @Id
    private String id;
    @GeoSpatialIndexed(type= GeoSpatialIndexType.GEO_2DSPHERE)
    // TODO: json custom deserializer
    private GeoJsonPoint location;
    private String city;
    private String postalCode;
    private String street;
    private String street2;
    private String street3;
    private String addressName;
    private String uuid;
    private String complexNumber;
    private Boolean showWarningMessage;
    private String todayOpen;
    private String locationType;
    private Boolean collectionPoint;
    private String sapStoreID;
    private String todayClose;

    public static Store of(StoreDto storeDto) {
        return Store.builder()
                .id(storeDto.getUuid())
                .city(storeDto.getCity())
                .postalCode(storeDto.getPostalCode())
                .street(storeDto.getStreet())
                .street2(storeDto.getStreet2())
                .street3(storeDto.getStreet3())
                .addressName(storeDto.getAddressName())
                .location(new GeoJsonPoint(Double.parseDouble(storeDto.getLongitude())
                        , Double.parseDouble(storeDto.getLatitude())))
                .locationType(storeDto.getLocationType())
                .todayOpen(storeDto.getTodayOpen())
                .todayClose(storeDto.getTodayClose())
                .complexNumber(storeDto.getComplexNumber())
                .sapStoreID(storeDto.getSapStoreID())
                .build();
    }
}
