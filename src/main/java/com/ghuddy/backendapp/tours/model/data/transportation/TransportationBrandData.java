package com.ghuddy.backendapp.tours.model.data.transportation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.transportation.TransportationBrandEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransportationBrandData {
    @Schema(description = "The id of the transportation brand", example = "1")
    @JsonProperty("transportation_brand_id")
    private Long transportationBrandId;
    @Schema(description = "The brand name of the transportation", example = "Hyundai")
    @JsonProperty("transportation_brand_name")
    private String transportationBrandName;

    public TransportationBrandData(TransportationBrandEntity transportationBrandEntity) {
        this.transportationBrandId = transportationBrandEntity.getId();
        this.transportationBrandName = transportationBrandEntity.getBrandName();
    }
}
