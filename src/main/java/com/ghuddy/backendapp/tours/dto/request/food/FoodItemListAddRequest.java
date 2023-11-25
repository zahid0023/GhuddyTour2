package com.ghuddy.backendapp.tours.dto.request.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class FoodItemListAddRequest extends BaseRequest {
    @Schema(description = "The list of the food items that will be stored in the database",required = true)
    @JsonProperty("food_items")
    private List<FoodItemRequest> foodItems;

    @Override
    public void validate() throws AbstractException {

    }
}
