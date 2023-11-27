package com.ghuddy.backendapp.tours.es.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.model.entities.ESSubscribedTourItineraryDocument;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

import java.time.LocalTime;
import java.util.List;

@Data
public class  ESSubscribedTourItineraryData {
    @JsonProperty("activity_id")
    @Field(name = "activity_id")
    private Long activityId;
    @JsonProperty("activity_name")
    @Field(name = "activity_name")
    private String activityName;
    @JsonProperty("short_address")
    @Field(name = "short_address")
    private String shortAddress;
    @JsonProperty("activity_day_number")
    @Field(name = "day_number")
    private Integer dayNumber;
    @JsonProperty("activity_start_time")
    @Field(name = "start_time")
    private LocalTime startTime;
    @JsonProperty("activity_end_time")
    @Field(name = "end_time")
    private LocalTime endTime;
    @JsonProperty("activity_images")
    @Field(name = "activity_images")
    private List<ESImageData> esImageDataList;

    public ESSubscribedTourItineraryData(ESSubscribedTourItineraryDocument esSubscribedTourItineraryDocument) {
        this.activityId = esSubscribedTourItineraryDocument.getActivityId();
        this.activityName = esSubscribedTourItineraryDocument.getActivityName();
        this.shortAddress = esSubscribedTourItineraryDocument.getShortAddress();
        this.dayNumber = esSubscribedTourItineraryDocument.getDayNumber();
        this.startTime = LocalTime.parse(esSubscribedTourItineraryDocument.getStartTime());
        this.endTime = LocalTime.parse(esSubscribedTourItineraryDocument.getEndTime());
        this.esImageDataList = esSubscribedTourItineraryDocument.getEsImageDataList().stream()
                .map(activityImageEntity -> new ESImageData(activityImageEntity))
                .toList();
    }
}
