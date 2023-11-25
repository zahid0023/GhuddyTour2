package com.ghuddy.backendapp.tours.model.data.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.activity.ActivityImageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ActivityImageData {
    @Schema(description = "The id of the activity image", example = "1")
    @JsonProperty("activity_image_id")
    private Long activityImageId;
    @Schema(description = "The file name of the image", example = "image1.jpg")
    @JsonProperty("activity_image_file_name")
    private String activityImageFileName;
    @Schema(description = "The url of the image", example = "www.image.com")
    @JsonProperty("activity_image_url")
    private String activityImageUrl;

    @Schema(description = "The caption of the image", example = "A caption")
    @JsonProperty("activity_image_caption")
    private String activityImageCaption;

    public ActivityImageData(ActivityImageEntity activityImageEntity) {
        this.activityImageId = activityImageEntity.getId();
        this.activityImageFileName = activityImageEntity.getFileName();
        this.activityImageUrl = activityImageEntity.getImageUrl();
        this.activityImageCaption = activityImageEntity.getCaption();
    }
}
