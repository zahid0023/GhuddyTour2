package com.ghuddy.backendapp.tours.es.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.activity.ActivityEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ESActivityDocument {
    @JsonProperty("activity_name")
    @Field(name = "activity_name")
    private String activityName;
    @JsonProperty("activity_images")
    @Field(name = "activity_images")
    private List<ESImageDocument> esImageDocumentList;

    public ESActivityDocument(ActivityEntity activityEntity){
        this.activityName = activityEntity.getActivityName();
        this.esImageDocumentList = activityEntity.getActivityImageEntities().stream()
                .map(activityImageEntity -> new ESImageDocument(activityImageEntity))
                .toList();
    }
}
