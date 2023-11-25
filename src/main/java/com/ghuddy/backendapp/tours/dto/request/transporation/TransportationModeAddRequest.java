package com.ghuddy.backendapp.tours.dto.request.transporation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TransportationModeAddRequest extends BaseRequest {
    @Schema(description = "The transportation mode that will be stored in the database", required = true)
    @JsonProperty("transportation_mode_name")
    private TransportationModeRequest transportationMode;

    @Override
    public void validate() throws AbstractException {

    }
}
