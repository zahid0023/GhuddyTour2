package com.ghuddy.backendapp.tours.model.data.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.food.FoodItemEntity;
import com.ghuddy.backendapp.tours.model.entities.food.MealPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class FlatMealPackageData {
    @Schema(description = "The meal package id")
    @JsonProperty("meal_package_id")
    private Long mealPackageId;
    @Schema(description = "The meal type id of this specific meal package")
    @JsonProperty("meal_type_id")
    private Long mealTypeId;
    @Schema(description = "The name of the meal package type")
    @JsonProperty("meal_type_name")
    private String mealTypeName;
    @Schema(description = "The food items belonging to this meal package")
    @JsonProperty("food_items")
    private List<FoodItemData> foodItems;

    @Schema(description = "The price of this meal package", required = true, example = "120")
    @JsonProperty("per_meal_package_price")
    private BigDecimal unitPrice;

    public FlatMealPackageData(MealPackageEntity mealPackageEntity) {
        this.mealPackageId = mealPackageEntity.getId();
        this.mealTypeId = mealPackageEntity.getMealTypeEntity().getId();
        this.mealTypeName = mealPackageEntity.getMealTypeEntity().getMealTypeName();
        this.foodItems = mealPackageEntity.getFoodItemEntities().stream()
                .map(foodItemEntity -> new FoodItemData(foodItemEntity))
                .toList();
        this.unitPrice = mealPackageEntity.getPerMealPrice();
    }
}
