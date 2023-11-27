package com.ghuddy.backendapp.tours.es.model.entities;

import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourItineraryEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ESSubscribedTourItineraryDocument {

    @Field(name = "activity_id", type = FieldType.Long)
    private Long activityId;
    @Field(name = "activity_name", type = FieldType.Text)
    private String activityName;
    @Field(name = "short_address", type = FieldType.Text)
    private String shortAddress;
    @Field(name = "day_number", type = FieldType.Integer)
    private Integer dayNumber;
    @Field(name = "start_time", type = FieldType.Date, format = DateFormat.custom, pattern = "HH:mm:ss")
    private String startTime;
    @Field(name = "end_time", type = FieldType.Date, format = DateFormat.custom, pattern = "HH:mm:ss")
    private String endTime;
    @Field(name = "activity_images", type = FieldType.Nested, includeInParent = true)
    private List<ESImageDocument> esImageDataList;

    public ESSubscribedTourItineraryDocument(SubscribedTourItineraryEntity subscribedTourItineraryEntity) {
        this.activityId = subscribedTourItineraryEntity.getActivityEntity().getId();
        this.activityName = subscribedTourItineraryEntity.getActivityEntity().getActivityName();
        this.shortAddress = subscribedTourItineraryEntity.getActivityEntity().getShortLocation();
        this.dayNumber = subscribedTourItineraryEntity.getActivityDayNumber();
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
        this.startTime = subscribedTourItineraryEntity.getActivityStartTime().format(isoFormatter);
        this.endTime = subscribedTourItineraryEntity.getActivityEndTime().format(isoFormatter);
        this.esImageDataList = subscribedTourItineraryEntity.getActivityEntity().getActivityImageEntities().stream()
                .map(activityImageEntity -> new ESImageDocument(activityImageEntity))
                .toList();
    }
}
