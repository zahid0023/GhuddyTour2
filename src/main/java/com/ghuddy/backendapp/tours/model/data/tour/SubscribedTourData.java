package com.ghuddy.backendapp.tours.model.data.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.activity.SubscribedTourActivityData;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class SubscribedTourData {
    @Schema(description = "The id of the created tour")
    @JsonProperty("created_tour_id")
    private Long createdTourId;
    @Schema(description = "The id of the subscribed tour")
    @JsonProperty("subscribed_tour_id")
    private Long subscribedTourId;
    @Schema(description = "The name of the tour that the merchant want to subscribe to", example = "1")
    @JsonProperty("subscribed_tour_name")
    private String subscribedTourName;
    @Schema(description = "The tour activities of the tour that merchant must turn into itinerary by providing day number, start time, and end time for each activity")
    @JsonProperty("subscribed_tour_itinerary")
    private List<SubscribedTourActivityData> subscribedTourActivityList;
    @Schema(description = "The tour reporting time on the first day", example = "08:30:00")
    @JsonProperty("subscribed_tour_reporting_time")
    private LocalTime tourReportingTime;
    @Schema(description = "The tour reporting place on the first day", example = "bandarban bus terminal, bandarban")
    @JsonProperty("subscribed_tour_reporting_place")
    private String tourReportingPlace;

    public SubscribedTourData(SubscribedTourEntity subscribedTourEntity) {
        this.createdTourId =subscribedTourEntity.getTourEntity().getId();
        this.subscribedTourId = subscribedTourEntity.getId();
        this.subscribedTourName = subscribedTourEntity.getTourEntity().getAddedTourEntity().getTourName();
        this.tourReportingTime = subscribedTourEntity.getTourReportingTime();
        this.tourReportingPlace = subscribedTourEntity.getTourReportingPlace();
        this.subscribedTourActivityList = subscribedTourEntity.getSubscribedTourItineraryEntities().stream()
                .map(subscribedTourItineraryEntity -> new SubscribedTourActivityData(subscribedTourItineraryEntity))
                .collect(Collectors.toList());
    }
}
