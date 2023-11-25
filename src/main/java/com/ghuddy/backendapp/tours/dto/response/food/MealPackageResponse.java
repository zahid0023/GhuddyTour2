package com.ghuddy.backendapp.tours.dto.response.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.food.FoodItemData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MealPackageResponse {
    @Schema(description = "The ID of the mealType", example = "1")
    @JsonProperty("meal_type_id")
    private Long mealTypeID; // Lunch
    @Schema(description = "The list of IDs of all the food items belonging to this package")
    @JsonProperty("food_items")
    private List<FoodItemData> foodItemDataList;
    @Schema(description = "The price of this meal package", required = true, example = "120")
    @JsonProperty("meal_package_unit_price")// per meal package price
    private BigDecimal perMealPackagePrice;
}
