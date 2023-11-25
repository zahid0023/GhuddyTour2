package com.ghuddy.backendapp.tours.model.data.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.activity.ActivityTypeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ActivityTypeData {
    @Schema(description = "The id of the activity type", example = "1")
    @JsonProperty("activity_type_id")
    private Long activityTypeId;
    @Schema(description = "The name of the activity type", example = "Hiking")
    @JsonProperty("activity_type_name")
    private String activityTypeName;
    @Schema(description = "The description of the activity")
    @JsonProperty("activity_type_description")
    private String activityTypeDescription;

    public ActivityTypeData(ActivityTypeEntity activityTypeEntity) {
        this.activityTypeId = activityTypeEntity.getId();
        this.activityTypeName = activityTypeEntity.getActivityTypeName();
        this.activityTypeDescription = activityTypeEntity.getDescription();
    }
}
