package com.ghuddy.backendapp.tours.dto.response.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageTypeData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TourPackageTypeListResponse {
    @Schema(description = "The list of tour package type")
    @JsonProperty("tour_package_types")
    private List<TourPackageTypeData> tourPackageTypeDataList;

    public TourPackageTypeListResponse(List<TourPackageTypeData> tourPackageTypeDataList) {
        this.tourPackageTypeDataList = tourPackageTypeDataList;
    }
}
