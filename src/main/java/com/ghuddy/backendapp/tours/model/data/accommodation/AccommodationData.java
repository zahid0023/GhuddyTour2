package com.ghuddy.backendapp.tours.model.data.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.accommodation.TourAccommodationEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AccommodationData {
    @Schema(description = "The id of the accommodation", example = "1")
    @JsonProperty("tour_accommodation_id")
    private Long tourAccommodationId;
    @Schema(description = "The name of the accommodation", example = "Hotel Aranna")
    @JsonProperty("tour_accommodation_name")
    private String tourAccommodationName;
    @Schema(description = "The type of the accommodation", example = "Hotel")
    @JsonProperty("tour_accommodation_type")
    private String tourAccommodationType;
    @Schema(description = "The address of the accommodation", example = "Bandarban, Chittagong")
    @JsonProperty("tour_accommodation_address")
    private String tourAccommodationAddress;
    @Schema(description = "The average rating of the accommodation", example = "4.7")
    @JsonProperty("tour_accommodation_average_rating")
    private BigDecimal tourAccommodationAverageRating;
    @Schema(description = "The total number of reviews")
    @JsonProperty("tour_accommodation_total_reviews")
    private Integer tourAccommodationTotalReviews;

    public AccommodationData(TourAccommodationEntity tourAccommodationEntity) {
        this.tourAccommodationId = tourAccommodationEntity.getId();
        this.tourAccommodationName = tourAccommodationEntity.getAccommodationName();
        this.tourAccommodationType = tourAccommodationEntity.getTourAccommodationTypeEntity().getAccommodationTypeName();
        this.tourAccommodationAddress = tourAccommodationEntity.getAccommodationAddress();
        this.tourAccommodationAverageRating = tourAccommodationEntity.getAverageRating();
        this.tourAccommodationTotalReviews = tourAccommodationEntity.getTotalReviews();
    }
}
