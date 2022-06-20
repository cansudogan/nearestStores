package com.jumbo.neareststores.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "stores")
public class Store {
    @Id
    private String id;
    private String city;
    private String postalCode;
    private String street;
    private String street2;
    private String street3;
    private String addressName;
    private String uuid;
    private Double longitude;
    private Double latitude;
    private Integer complexNumber;
    private Boolean showWarningMessage;
    private OffsetDateTime todayOpen;
    private String locationType;
    private Boolean collectionPoint;
    private Integer sapStoreID;
    private OffsetDateTime todayClose;
}
