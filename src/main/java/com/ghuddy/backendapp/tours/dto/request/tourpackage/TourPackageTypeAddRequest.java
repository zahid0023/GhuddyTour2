package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourPackageTypeAddRequest extends BaseRequest {

    @Schema(description = "The tour package to be stored in the database", required = true)
    @JsonProperty("tour_packages")
    private TourPackageTypeRequest tourPackageTypeRequest;

    @Override
    public void validate() throws AbstractException {

    }
}
