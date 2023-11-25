package com.ghuddy.backendapp.tours.dto.request.transporation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TransportationPackageListAddRequest extends BaseRequest {
    @Schema(description = "The tour package id", required = true, example = "1")
    @JsonProperty("subscribed_tour_id")
    private Long subscribedTourId;
    @Schema(description = "The list of transportations that will be associated with the tour package")
    @JsonProperty("tour_transportation_packages")
    private List<TransportationPackageRequest> tourPackageTransportations;

    @Override
    public void validate() throws AbstractException {

    }
}
