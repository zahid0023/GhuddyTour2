package com.ghuddy.backendapp.tours.dto.request.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourSpecialityRequest {
    @Schema(description = "The title of the tour speciality.", example = "Adventure")
    @JsonProperty("tour_speciality_title")
    private String tourSpecialityTitle;

    @Schema(description = "The description of the tour speciality.", example = "Exciting adventures for thrill-seekers.")
    @JsonProperty("tour_speciality_description")
    private String tourSpecialityDescription;

    @Schema(description = "The URL of the icon representing the tour speciality.", example = "https://example.com/icons/adventure.png")
    @JsonProperty("tour_speciality_icon_url")
    private String tourSpecialityIconURL;
}



