package com.ghuddy.backendapp.tours.dto.request.transporation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TransportationModeListAddRequest extends BaseRequest {
    @Schema(description = "The list of transportation mode that will be stored in the database", required = true)
    @JsonProperty("transportation_modes")
    private List<TransportationModeRequest> transportationModes;

    @Override
    public void validate() throws AbstractException {

    }
}
