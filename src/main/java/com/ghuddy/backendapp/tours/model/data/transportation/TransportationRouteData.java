package com.ghuddy.backendapp.tours.model.data.transportation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.transportation.TransportationRouteEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransportationRouteData {
    @Schema(description = "The id of the route", example = "1")
    @JsonProperty("transportation_route_id")
    private Long transportationRouteId;
    @Schema(description = "The location from where the traveler will be given transportation facility", example = "Dhaka")
    @JsonProperty("transportation_route_source_location")
    private String transportationRouteSourceLocation;
    @Schema(description = "The location to where the traveler will be given transportation facility", example = "Bandarban")
    @JsonProperty("transportation_route_destination_location")
    private String transportationRouteDestinationLocation;

    public TransportationRouteData(TransportationRouteEntity transportationRouteEntity) {
        this.transportationRouteId = transportationRouteEntity.getId();
        this.transportationRouteSourceLocation = transportationRouteEntity.getSourceLocation().getPlaceName();
        this.transportationRouteDestinationLocation = transportationRouteEntity.getDestinationLocation().getPlaceName();
    }
}
