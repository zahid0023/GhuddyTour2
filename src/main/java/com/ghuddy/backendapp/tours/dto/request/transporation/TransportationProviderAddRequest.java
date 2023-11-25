package com.ghuddy.backendapp.tours.dto.request.transporation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TransportationProviderAddRequest extends BaseRequest {
    @Schema(description = "The transportation provider that will be stored in the database", required = true)
    @JsonProperty("transportation_provider")
    private TransportationProviderRequest transportationProvider;

    @Override
    public void validate() throws AbstractException {

    }
}
