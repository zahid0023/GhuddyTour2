package com.ghuddy.backendapp.tours.es.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourDocument;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class ESTourData {
    @JsonProperty("tour_id")
    private Long tourId;
    @JsonProperty("tour_name")
    private String tourName;
    @JsonProperty("short_address")
    private String shortAddress;
    @JsonProperty("number_of_reviews")
    private Integer numberOfReviews;
    @JsonProperty("rating_in_stars")
    private Double ratingInStars;
    @JsonProperty("tour_thumb_image_url")
    private String tourThumbImageUrl;
    @JsonProperty("tour_specialities")
    private List<ESTourSpecialityData> esTourSpecialityDataList;
    @JsonProperty("tour_title")
    private String tourTitle;
    @JsonProperty("tour_description")
    private String tourDescription;
    @JsonProperty("tour_reporting_time")
    private LocalTime tourReportingTime;
    @JsonProperty("tour_reporting_place")
    private String tourReportingPlace;
    @JsonProperty("tour_tag")
    private String tourTag;

    public ESTourData(ESTourDocument esTourDocument){
        this.tourId = esTourDocument.getTourId();
        this.tourName = esTourDocument.getTourName();
        this.shortAddress = esTourDocument.getShortAddress();
        this.numberOfReviews = esTourDocument.getNumberOfReviews();
        this.ratingInStars = esTourDocument.getRatingInStars();
        this.tourThumbImageUrl = esTourDocument.getTourThumbImageUrl();
        this.esTourSpecialityDataList = esTourDocument.getEsTourSpecialityDocumentList().stream()
                .map(esTourSpecialityDocument -> new ESTourSpecialityData(esTourSpecialityDocument))
                .toList();
        this.tourTitle = esTourDocument.getTourTitle();
        this.tourDescription = esTourDocument.getTourDescription();
        this.tourReportingTime = esTourDocument.getTourReportingTime();
        this.tourReportingPlace = esTourDocument.getTourReportingPlace();
        this.tourTag = esTourDocument.getTourTag();
    }
}
