package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TourPackageTypeListAddRequest extends BaseRequest {
    @Schema(description = "The list of tour package types that will be stored in the database", required = true)
    @JsonProperty("tour_package_types")
    private List<TourPackageTypeRequest> tourPackageTypes;

    @Override
    public void validate() throws AbstractException {

    }
}
