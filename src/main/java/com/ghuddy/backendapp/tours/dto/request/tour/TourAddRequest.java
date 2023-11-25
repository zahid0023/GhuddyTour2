package com.ghuddy.backendapp.tours.dto.request.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourAddRequest extends BaseRequest {
    @Schema(required = true,
            description = "The ID of the Location",
            example = "1")
    @JsonProperty("destination_location_id")
    private Long locationID;
    @Schema(required = true,
            description = "Specify the the number of days of the tour",
            example = "2")
    @JsonProperty("number_of_days")
    private Integer numberOfDays;
    @Schema(required = true,
            description = "Specify the the number of nights of the tour",
            example = "1")
    @JsonProperty("number_of_nights")
    private Integer numberOfNights;
    @Schema(required = true,
            description = "Short address of the tour",
            example = "Bandarban, Chittagong")
    @JsonProperty("short_address")
    private String shortAddress;

    @Override
    public void validate() throws AbstractException {

    }
}
