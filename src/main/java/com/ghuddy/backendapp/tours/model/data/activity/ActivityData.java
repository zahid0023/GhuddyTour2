package com.ghuddy.backendapp.tours.model.data.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.activity.ActivityEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ActivityData {
    @JsonProperty("activity_id")
    private Long activityId;
    @JsonProperty("activity_name")
    private String activityName;
    @JsonProperty("activity_short_location")
    private String activityShortLocation;
    @JsonProperty("activity_type_name")
    private String activityTypeName;
    @JsonProperty("activity_images")
    private List<ActivityImageData> activityImages;

    public ActivityData(ActivityEntity activityEntity) {
        this.activityId = activityEntity.getId();
        this.activityName = activityEntity.getActivityName();
        this.activityShortLocation = activityEntity.getShortLocation();
        this.activityTypeName = activityEntity.getActivityTypeEntity().getActivityTypeName();
        this.activityImages = activityEntity.getActivityImageEntities()
                .stream()
                .map(activityImageEntity -> new ActivityImageData(activityImageEntity))
                .collect(Collectors.toList());
    }
}
