package com.ghuddy.backendapp.tours.dto.response.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.food.FoodItemData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class FoodItemListResponse extends BaseResponse {
    @Schema(description = "The list of the food items", required = true)
    @JsonProperty("food_items")
    List<FoodItemData> foodItems;

    public FoodItemListResponse(List<FoodItemData> foodItems) {
        this.foodItems = foodItems;
    }
}
