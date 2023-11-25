package com.ghuddy.backendapp.tours.model.data.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.food.MealTypeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MealTypeData {
    @Schema(description = "The id of the meal type", required = true, example = "1")
    @JsonProperty("meal_type_id")
    private Long mealTypeId;
    @Schema(description = "The name of the meal type", required = true, example = "Lunch")
    @JsonProperty("meal_type_name")
    private String mealTypeName;

    public MealTypeData(MealTypeEntity mealTypeEntity) {
        this.mealTypeId = mealTypeEntity.getId();
        this.mealTypeName = mealTypeEntity.getMealTypeName();

    }
}
