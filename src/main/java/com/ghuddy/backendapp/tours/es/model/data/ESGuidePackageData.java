package com.ghuddy.backendapp.tours.es.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.model.entities.ESGuidePackageDocument;
import lombok.Data;

@Data
public class ESGuidePackageData {
    @JsonProperty("number_of_guides_for_day")
    private Integer numberOfGuidesForDay;

    @JsonProperty("day_number")
    private Integer dayNumber;
    public ESGuidePackageData (ESGuidePackageDocument esGuidePackageDocument){
        this.numberOfGuidesForDay = esGuidePackageDocument.getNumberOfGuidesForDay();
        this.dayNumber = esGuidePackageDocument.getDayNumber();
    }
}
