package com.ghuddy.backendapp.tours.dto.response.transportation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationProviderData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TransportationProviderListResponse{
    @Schema(description = "The list of transportation providers")
    @JsonProperty("transportation_providers")
    private List<TransportationProviderData> transportationProviderDataList;

    public TransportationProviderListResponse(List<TransportationProviderData> transportationProviderDataList) {
        this.transportationProviderDataList = transportationProviderDataList;
    }
}
