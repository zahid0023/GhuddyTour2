package com.ghuddy.backendapp.tours.dto.response.commons;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AddressResponse extends BaseResponse {
    @Schema(description = "The short address related to the tour and its activities")
    @JsonProperty("short_addresses")
    private List<String> shortAddresses;

    public AddressResponse(List<String> shortAddresses, String requestId) {
        super(requestId);
        this.shortAddresses = shortAddresses;
    }
}
