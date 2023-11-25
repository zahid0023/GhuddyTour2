package com.ghuddy.backendapp.tours.model.data.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourItineraryEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
public class SubscribedTourActivityData {
    @JsonProperty("activity_id")
    private Long activityId;
    @JsonProperty("activity_name")
    private String activityName;
    @JsonProperty("short_address")
    private String shortAddress;
    @JsonProperty("activity_day_number")
    private Integer dayNumber;
    @JsonProperty("activity_start_time")
    private LocalTime startTime;
    @JsonProperty("activity_end_time")
    private LocalTime endTime;

    @JsonProperty("activity_images")
    private List<ActivityImageData> activityImageDataList;

    public SubscribedTourActivityData(SubscribedTourItineraryEntity subscribedTourItineraryEntity) {
        this.activityId = subscribedTourItineraryEntity.getActivityEntity().getId();
        this.activityName = subscribedTourItineraryEntity.getActivityEntity().getActivityName();
        this.shortAddress = subscribedTourItineraryEntity.getActivityEntity().getShortLocation();
        this.dayNumber = subscribedTourItineraryEntity.getActivityDayNumber();
        this.startTime = subscribedTourItineraryEntity.getActivityStartTime();
        this.endTime = subscribedTourItineraryEntity.getActivityEndTime();
        this.activityImageDataList = subscribedTourItineraryEntity.getActivityEntity().getActivityImageEntities().stream()
                .map(activityImageEntity -> new ActivityImageData(activityImageEntity))
                .toList();
    }
}
