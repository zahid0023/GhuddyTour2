package com.ghuddy.backendapp.tours.dto.response.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.tour.TourData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TourListResponse extends BaseResponse {
    @Schema(description = "The list of all the tours")
    @JsonProperty("tours")
    private final List<TourData> tourDataList;

    public TourListResponse(List<TourData> tourDataList, String requestId) {
        this.tourDataList = tourDataList;
        this.setRequestId(requestId);
    }
}
