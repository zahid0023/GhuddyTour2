package com.ghuddy.backendapp.tours.dto.request.spot.entry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SpotEntryPackageAddRequest extends BaseRequest {
    @Schema(description = "The tour package id that belongs to this spot entry")
    @JsonProperty("subscribed_tour_id")
    private Long subscribedTourId;

    @Schema(description = "The spot entry request", required = true)
    @JsonProperty("tour_spot_entry_package")
    private SpotEntryPackageRequest spotEntryPackageRequest;

    /**
     * @throws AbstractException
     */
    @Override
    public void validate() throws AbstractException {

    }
}
