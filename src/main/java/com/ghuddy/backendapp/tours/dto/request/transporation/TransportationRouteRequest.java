package com.ghuddy.backendapp.tours.dto.request.transporation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TransportationRouteRequest {
    @Schema(description = "The id of the location from where the transportation service will be provided", required = true, example = "1")
    @JsonProperty("source_location_id")
    private Long sourceLocationID;
    @Schema(description = "The id of the location to where the transportation service will be provided", required = true, example = "1")
    @JsonProperty("destination_location_id")
    private Long destinationLocationID;
}
