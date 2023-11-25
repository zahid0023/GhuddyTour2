package com.ghuddy.backendapp.tours.dto.request.transporation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TransportationRouteListAddRequest extends BaseRequest {
    @Schema(description = "The list of the transportation route that will be stored in the database", required = true)
    @JsonProperty("transportation_routes")
    private List<TransportationRouteRequest> transportationRoutes;

    @Override
    public void validate() throws AbstractException {

    }
}
