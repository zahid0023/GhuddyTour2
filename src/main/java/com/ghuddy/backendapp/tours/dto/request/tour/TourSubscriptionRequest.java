package com.ghuddy.backendapp.tours.dto.request.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import com.ghuddy.backendapp.tours.dto.request.activity.SubscribedTourActivityRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class TourSubscriptionRequest extends BaseRequest {
    @Schema(description = "The id of the tour that the merchant want to subscribe to", required = true, example = "1")
    @JsonProperty("tour_id")
    private Long tourId;
    @Schema(description = "The id of the merchant who wants to subscribe the tour i.e. provide the tour", required = true, example = "1")
    @JsonProperty("merchant_id")
    private Long merchantId;
    @Schema(description = "The tour reporting time on the first day", required = true, example = "08:30:00")
    @JsonProperty("tour_reporting_time")
    private LocalTime tourReportingTime;
    @Schema(description = "The tour reporting place on the first day", required = true, example = "bandarban bus terminal, bandarban")
    @JsonProperty("tour_starting_place")
    private String tourReportingPlace;

    @Schema(description = "The tour activities of the tour that merchant must turn into itinerary by providing day number, start time, and end time for each activity", required = true)
    @JsonProperty("tour_activities")
    private List<SubscribedTourActivityRequest> subscribedTourActivityList;

    /**
     * @throws AbstractException
     */
    @Override
    public void validate() throws AbstractException {

    }
}
