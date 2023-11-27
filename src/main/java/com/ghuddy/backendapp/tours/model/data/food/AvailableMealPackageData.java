package com.ghuddy.backendapp.tours.model.data.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.food.AvailableMealPackageEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AvailableMealPackageData {
    @JsonProperty("meal_type_id")
    private Long mealTypeId;
    @JsonProperty("meal_type_name")
    private String mealTypeName;
    @JsonProperty("food_items")
    private List<FoodItemData> foodItemDataList;
    @JsonProperty("meal_package_price")
    private BigDecimal mealPackagePrice;

    public AvailableMealPackageData(AvailableMealPackageEntity availableMealPackageEntity){
        this.mealTypeId = availableMealPackageEntity.getMealPackageEntity().getMealTypeEntity().getId();
        this.mealTypeName = availableMealPackageEntity.getMealPackageEntity().getMealTypeEntity().getMealTypeName();
        this.foodItemDataList = availableMealPackageEntity.getMealPackageEntity().getFoodItemEntities().stream()
                .map(foodItemEntity -> new FoodItemData(foodItemEntity))
                .toList();
        this.mealPackagePrice = availableMealPackageEntity.getMealPackagePrice();
    }
}
