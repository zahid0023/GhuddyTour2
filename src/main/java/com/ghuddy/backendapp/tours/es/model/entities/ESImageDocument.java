package com.ghuddy.backendapp.tours.es.model.entities;

import com.ghuddy.backendapp.tours.model.entities.activity.ActivityImageEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
public class ESImageDocument {
    @Field(name = "image_id", type = FieldType.Long)
    private Long imageId;
    @Field(name = "image_file_name", type = FieldType.Text)
    private String imageFileName;
    @Field(name = "image_url", type = FieldType.Text)
    private String imageUrl;
    @Field(name = "image_caption", type = FieldType.Text)
    private String imageCaption;

    public ESImageDocument(ActivityImageEntity activityImageEntity) {
        this.imageId = activityImageEntity.getId();
        this.imageFileName = activityImageEntity.getFileName();
        this.imageUrl = activityImageEntity.getImageUrl();
        this.imageCaption = activityImageEntity.getCaption();
    }
}
