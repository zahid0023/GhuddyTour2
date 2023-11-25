package com.ghuddy.backendapp.tours.dto.request.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FoodItemRequest {
    @Schema(description = "The name of the food item that will be stored in the database", required = true, example = "Rice")
    @JsonProperty("food_item_name")
    private String foodItemName;
}