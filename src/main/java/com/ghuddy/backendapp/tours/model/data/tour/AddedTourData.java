package com.ghuddy.backendapp.tours.model.data.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.tour.AddedTourEntity;
import lombok.Data;

@Data
public class AddedTourData {
    @JsonProperty("added_tour_id")
    private Long addedTourId;
    @JsonProperty("added_tour_name")
    private String addedTourName;
    @JsonProperty("destination_location")
    private String destinationLocation;
    @JsonProperty("number_of_days")
    private Integer numberOfDays;
    @JsonProperty("number_of_nights")
    private Integer numberOfNights;
    @JsonProperty("short_address")
    private String shortAddress;
    @JsonProperty("added_tour_tag")
    private String addedTourTag;

    public AddedTourData(AddedTourEntity addedTourEntity){
        this.addedTourId = addedTourEntity.getId();
        this.addedTourName = addedTourEntity.getTourName();
        this.destinationLocation = addedTourEntity.getDestinationLocationEntity().getPlaceName();
        this.numberOfDays = addedTourEntity.getNumberOfDays();
        this.numberOfNights = addedTourEntity.getNumberOfNights();
        this.shortAddress = addedTourEntity.getShortAddress();
        this.addedTourTag = addedTourEntity.getTourTag();

    }

}
