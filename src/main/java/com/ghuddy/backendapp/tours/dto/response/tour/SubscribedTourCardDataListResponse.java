package com.ghuddy.backendapp.tours.dto.response.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.tour.SubscribedTourData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class SubscribedTourCardDataListResponse extends BaseResponse {
    @Schema(description = "List of subscribed tours")
    @JsonProperty("subscribed_tours")
    private List<SubscribedTourData> subscribedTourDataList;
    @Schema(description = "number of total pages")
    @JsonProperty("total_pages")
    private Integer totalPages;
    @Schema(description = "number of total elements")
    @JsonProperty("number_of_elements")
    private Long numberOfElements;

    public SubscribedTourCardDataListResponse(List<SubscribedTourData> subscribedTourDataList, String requestId, Integer totalPages, Long numberOfElements) {
        this.subscribedTourDataList = subscribedTourDataList;
        this.setRequestId(requestId);
        this.totalPages = totalPages;
        this.numberOfElements = numberOfElements;
    }
}
