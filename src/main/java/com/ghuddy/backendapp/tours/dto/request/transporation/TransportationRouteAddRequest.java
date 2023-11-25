package com.ghuddy.backendapp.tours.dto.request.transporation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TransportationRouteAddRequest extends BaseRequest {
    @Schema(description = "The route that will be stored in the database", required = true)
    @JsonProperty("transportation_route")
    private TransportationRouteRequest transportationRoute;

    @Override
    public void validate() throws AbstractException {

    }
}
