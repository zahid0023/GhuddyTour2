package com.ghuddy.backendapp.tours.dto.request.availability.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MealPackageRequestForAvailability {
    @Schema(description = "The meal package id", required = true, example = "1")
    @JsonProperty("tour_package_meal_package_id")
    private Long mealPackageId;
    @Schema(description = "The meal package price", required = true, example = "200")
    @JsonProperty("per_meal_package_price")
    private BigDecimal mealPackagePrice;
}
