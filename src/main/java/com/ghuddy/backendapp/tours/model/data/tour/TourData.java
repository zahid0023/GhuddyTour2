package com.ghuddy.backendapp.tours.model.data.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TourData {
    @JsonProperty("tour_id")
    private Long tourId;
    @JsonProperty("tour_name")
    private String tourName;
    @JsonProperty("number_of_days")
    private Integer numberOfDays;
    @JsonProperty("number_of_nights")
    private Integer numberOfNights;
    @JsonProperty("tour_thumb_image_url")
    private String tourThumbImageUrl;
    @JsonProperty("tour_title")
    private String tourTitle;
    @JsonProperty("tour_description")
    private String tourDescription;
    @JsonProperty("tour_itinerary")
    private List<TourItineraryData> itinerary;
    @JsonProperty("tour_specialities")
    private List<TourSpecialityData> tourSpecialityDataList;

    public TourData(TourEntity tourEntity) {
        this.tourId = tourEntity.getId();
        this.tourName = tourEntity.getAddedTourEntity().getTourName();
        this.numberOfDays = tourEntity.getAddedTourEntity().getNumberOfDays();
        this.numberOfNights = tourEntity.getAddedTourEntity().getNumberOfNights();
        this.tourThumbImageUrl = tourEntity.getThumbImageUrl();
        this.tourTitle = tourEntity.getTitle();
        this.tourDescription = tourEntity.getDescription();
        this.itinerary = tourEntity.getTourItineraryEntities().stream()
                .map(tourItineraryEntity -> new TourItineraryData(tourItineraryEntity))
                .collect(Collectors.toList());
        this.tourSpecialityDataList = tourEntity.getTourSpecialityEntities().stream()
                .map(tourSpecialityEntity -> new TourSpecialityData(tourSpecialityEntity))
                .collect(Collectors.toList());
    }
}
