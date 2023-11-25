package com.ghuddy.backendapp.tours.model.data.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.accommodation.TourAccommodationTypeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccommodationTypeData {
    @Schema(description = "The id of the tour accommodation type", example = "1")
    @JsonProperty("tour_accommodation_type_id")
    private Long tourAccommodationTypeId;
    @Schema(description = "The name of the tour accommodation type", example = "Hotel")
    @JsonProperty("tour_accommodation_type_name")
    private String tourAccommodationTypeName;

    public AccommodationTypeData(TourAccommodationTypeEntity tourAccommodationTypeEntity) {
        this.tourAccommodationTypeId = tourAccommodationTypeEntity.getId();
        this.tourAccommodationTypeName = tourAccommodationTypeEntity.getAccommodationTypeName();
    }
}
