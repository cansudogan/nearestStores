package com.jumbo.neareststores.entity;

import com.jumbo.neareststores.model.Stores.StoreDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import static java.util.Objects.isNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Document(collection = "stores")
public class Store {
    @Id
    private String id;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
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
    private String todayClose;
    private String locationType;
    private Boolean collectionPoint;
    private String sapStoreID;

    public static Store of(StoreDto storeDto) {
        Boolean collectionPoint = isNull(storeDto.getCollectionPoint()) ? null : Boolean.valueOf(storeDto.getCollectionPoint());
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
                .collectionPoint(collectionPoint)
                .sapStoreID(storeDto.getSapStoreID())
                .build();
    }

    private static Integer convertTime(String s) {
        return Integer.parseInt(s.split(":")[0]) * 60 + Integer.parseInt(s.split(":")[1]);
    }
}
