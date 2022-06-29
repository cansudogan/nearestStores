package com.jumbo.neareststores.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchFilterRequest {
    private Boolean collectionPoint;
    private Boolean closed;

    @NotNull(message = "Latitude value cannot be null")
    private Double latitude;


    @NotNull(message = "Longitude value cannot be null")
    private Double longitude;

    @Min(value = 5, message = "Page size must not be less than five!")
    private Integer size = 5;

    @Min(value = 0, message = "Page index must not be less than zero!")
    private Integer page = 0;

    public Point createPoint() {
        return new GeoJsonPoint(latitude, longitude);
    }

    public Pageable createPage() {
        return PageRequest.of(page, size);
    }
}
