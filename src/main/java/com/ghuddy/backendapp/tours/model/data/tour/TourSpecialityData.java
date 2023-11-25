package com.ghuddy.backendapp.tours.model.data.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.tour.TourSpecialityEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TourSpecialityData {
    @JsonProperty("tour_speciality_id")
    private Long tourSpecialityId;
    @JsonProperty("tour_speciality_title")
    private String tourSpecialityTitle;
    @JsonProperty("tour_speciality_description")
    private String tourSpecialityDescription;
    @JsonProperty("tour_speciality_icon_url")
    private String tourSpecialityIconUrl;

    public TourSpecialityData(TourSpecialityEntity tourSpecialityEntity) {
        this.tourSpecialityId = tourSpecialityEntity.getId();
        this.tourSpecialityTitle = tourSpecialityEntity.getTitle();
        this.tourSpecialityDescription = tourSpecialityEntity.getDescription();
        this.tourSpecialityIconUrl = tourSpecialityEntity.getIconUrl();
    }
}
