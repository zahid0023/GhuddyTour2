package com.ghuddy.backendapp.tours.dto.request.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MealTypeRequest {
    @Schema(description = "The name of the meal type", required = true, example = "Lunch")
    @JsonProperty("meal_type_name")
    private String mealTypeName;

}
