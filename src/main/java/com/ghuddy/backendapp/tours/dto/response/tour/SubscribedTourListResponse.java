package com.ghuddy.backendapp.tours.dto.response.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.tour.SubscribedTourData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class SubscribedTourListResponse extends BaseResponse {
    @Schema(description = "The list of subscribed tours")
    @JsonProperty("subscribed_tours")
    private List<SubscribedTourData> subscribedTourDataList;

    public SubscribedTourListResponse(List<SubscribedTourData> subscribedTourDataList, String requestId) {
        this.subscribedTourDataList = subscribedTourDataList;
        this.setRequestId(requestId);
    }
}
