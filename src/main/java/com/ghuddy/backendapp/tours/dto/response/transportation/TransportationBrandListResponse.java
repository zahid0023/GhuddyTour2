package com.ghuddy.backendapp.tours.dto.response.transportation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationBrandData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TransportationBrandListResponse extends BaseResponse {
    @Schema(description = "The list of the transportation brand")
    @JsonProperty("transportation_brands")
    private List<TransportationBrandData> transportationBrandDataList;

    public TransportationBrandListResponse(List<TransportationBrandData> transportationBrandDataList) {
        this.transportationBrandDataList = transportationBrandDataList;
    }
}
