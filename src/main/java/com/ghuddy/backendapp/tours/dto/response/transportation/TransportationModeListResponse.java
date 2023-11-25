package com.ghuddy.backendapp.tours.dto.response.transportation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationModeData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TransportationModeListResponse extends BaseResponse {
    @Schema(description = "The list of transportation modes")
    @JsonProperty("transportation_modes")
    private List<TransportationModeData> transportationModeDataList;

    public TransportationModeListResponse(List<TransportationModeData> transportationModeDataList) {
        this.transportationModeDataList = transportationModeDataList;
    }
}
