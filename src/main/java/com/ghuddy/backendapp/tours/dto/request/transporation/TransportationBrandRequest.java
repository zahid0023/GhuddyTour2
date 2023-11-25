package com.ghuddy.backendapp.tours.dto.request.transporation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TransportationBrandRequest {
    @Schema(description = "The brand name",required = true, example = "Hyundai")
    @JsonProperty("transportation_brand_name")
    private String brandName;
}
