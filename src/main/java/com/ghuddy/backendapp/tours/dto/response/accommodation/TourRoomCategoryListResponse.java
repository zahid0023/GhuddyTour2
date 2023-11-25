package com.ghuddy.backendapp.tours.dto.response.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourRoomCategoryData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TourRoomCategoryListResponse extends BaseResponse {
    @Schema(description = "The list of room categories", required = true)
    @JsonProperty("tour_room_categories")
    private List<TourRoomCategoryData> tourRoomCategoryDataList;

    public TourRoomCategoryListResponse(List<TourRoomCategoryData> tourRoomCategoryDataList, String requestId) {
        this.tourRoomCategoryDataList = tourRoomCategoryDataList;
        this.setRequestId(requestId);
    }
}
