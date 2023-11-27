package com.ghuddy.backendapp.tours.es.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.model.entities.ESImageDocument;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
public class ESImageData {
    @JsonProperty("image_id")
    @Field(name = "image_id")
    private Long imageId;
    @JsonProperty("image_file_name")
    @Field(name = "image_file_name")
    private String imageFileName;
    @JsonProperty("image_url")
    @Field(name = "image_url")
    private String imageUrl;
    @JsonProperty("image_caption")
    @Field(name = "image_caption")
    private String imageCaption;

    public ESImageData(ESImageDocument esImageDocument) {
        this.imageId = esImageDocument.getImageId();
        this.imageFileName = esImageDocument.getImageFileName();
        this.imageUrl = esImageDocument.getImageUrl();
        this.imageCaption = esImageDocument.getImageCaption();
    }
}
