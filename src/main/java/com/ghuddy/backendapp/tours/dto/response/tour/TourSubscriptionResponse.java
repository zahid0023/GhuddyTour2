package com.ghuddy.backendapp.tours.dto.response.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.tour.SubscribedTourData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourSubscriptionResponse extends BaseResponse {
    @Schema(description = "Tour that merchant has subscribed")
    @JsonProperty("subscribed_tour")
    private SubscribedTourData subscribedTourData;

    public TourSubscriptionResponse(SubscribedTourData subscribedTourData, String requestId) {
        this.subscribedTourData = subscribedTourData;
        this.setRequestId(requestId);
    }
}
