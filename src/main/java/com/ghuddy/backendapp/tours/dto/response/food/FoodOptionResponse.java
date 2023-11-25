package com.ghuddy.backendapp.tours.dto.response.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FoodOptionResponse {
    @Schema(description = "The day number in which this food option is available")
    @JsonProperty("day_number")
    private Integer dayNumber;
    @JsonProperty("tour_package_meal_packages")
    private List<MealPackageResponse> mealPackageResponseList;
    @Schema(description = "Whether this is the default food option for the tour package", example = "false")
    @JsonProperty("tour_package_default_food_option")
    private Boolean isDefault;

    @Schema(description = "The meal price for the day")
    @JsonProperty("tour_package_food_option_price")
    private BigDecimal foodOptionTotalPrice;

    public FoodOptionResponse(List<MealPackageResponse> mealPackageResponseList, Integer dayNumber) {
        this.dayNumber = dayNumber;
        this.mealPackageResponseList = mealPackageResponseList;
        this.isDefault = false;
        this.foodOptionTotalPrice = mealPackageResponseList.stream()
                .map(mealPackageResponse -> mealPackageResponse.getPerMealPackagePrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
