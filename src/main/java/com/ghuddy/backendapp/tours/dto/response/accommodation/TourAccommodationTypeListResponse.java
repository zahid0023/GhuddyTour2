package com.ghuddy.backendapp.tours.dto.response.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.accommodation.AccommodationTypeData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TourAccommodationTypeListResponse extends BaseResponse {
    @Schema(description = "The list of accommodation types")
    @JsonProperty("tour_accommodation_types")
    private List<AccommodationTypeData> accommodationTypeDataList;

    public TourAccommodationTypeListResponse(List<AccommodationTypeData> accommodationTypeDataList, String requestId) {
        this.accommodationTypeDataList = accommodationTypeDataList;
        this.setRequestId(requestId);
    }
}
