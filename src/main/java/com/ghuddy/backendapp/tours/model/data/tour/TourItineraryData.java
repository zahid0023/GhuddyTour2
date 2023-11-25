package com.ghuddy.backendapp.tours.model.data.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.activity.ActivityImageData;
import com.ghuddy.backendapp.tours.model.entities.tour.TourItineraryEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TourItineraryData {
    @JsonProperty("tour_activity_id")
    private Long tourActivityId;
    @JsonProperty("tour_activity_name")
    private String tourActivityName;
    @JsonProperty("short_address")
    private String shortAddress;
    @JsonProperty("tour_activity_images")
    private List<ActivityImageData> activityImageDataList;

    public TourItineraryData(TourItineraryEntity tourItineraryEntity) {
        this.tourActivityId = tourItineraryEntity.getActivity().getId();
        this.tourActivityName = tourItineraryEntity.getActivity().getActivityName();
        this.shortAddress = tourItineraryEntity.getActivity().getShortLocation();
        this.activityImageDataList = tourItineraryEntity.getActivity().getActivityImageEntities().stream()
                .map(activityImageEntity -> new ActivityImageData(activityImageEntity))
                .collect(Collectors.toList());
    }
}
