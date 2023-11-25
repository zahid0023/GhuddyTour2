package com.ghuddy.backendapp.tours.model.data.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.tour.AddedTourEntity;
import lombok.Data;

@Data
public class AddedTourDataOptimized {
    @JsonProperty("added_tour_id")
    private Long addedTourId;
    @JsonProperty("added_tour_name")
    private String addedTourName;

    public AddedTourDataOptimized(AddedTourEntity addedTourEntity) {
        this.addedTourId = addedTourEntity.getId();
        this.addedTourName = addedTourEntity.getTourName();
    }
}
