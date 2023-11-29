package com.ghuddy.backendapp.tours.dto.request.sales;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourPackageOptionAddToCart {
    @Schema(description = "The available tour package id", required = true, example = "1")
    @JsonProperty("available_tour_package_id")
    private Long availableTourPackageId;
    @Schema(description = "The accommodation option id", required = true, example = "1")
    @JsonProperty("tour_package_accommodation_option_id")
    private Long accommodationOptionId;
    @Schema(description = "The food option id", required = true, example = "1")
    @JsonProperty("tour_package_food_option_id")
    private Long foodOptionId;
    @Schema(description = "The transfer option id", required = true, example = "1")
    @JsonProperty("tour_package_transfer_option_id")
    private Long transferOptionId;
    @Schema(description = "The transportation package id", required = true, example = "1")
    @JsonProperty("tour_package_transportation_package_id")
    private Long transportationPackageId;
    @Schema(description = "The guide option id", required = true, example = "1")
    @JsonProperty("tour_package_guide_option_id")
    private Long guideOptionId;
    @Schema(description = "The spot entry option id", required = true, example = "1")
    @JsonProperty("tour_package_spot_entry_option_id")
    private Long spotEntryOptionId;
    @Schema(description = "The times this option combination is purchased by the traveller", required = true, example = "1")
    @JsonProperty("tour_package_option_quantity")
    private Integer quantity;
}
