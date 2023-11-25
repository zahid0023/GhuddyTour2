package com.ghuddy.backendapp.tours.dto.response.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class FoodOptionCombinationCheckResponse {
    @JsonProperty("tour_package_food_options")
    private List<FoodOptionResponse> foodOptionResponseList;

    @JsonProperty("meal_type_name_id_map")
    private HashMap<String, Long> idMealTypeMap;

    public FoodOptionCombinationCheckResponse(List<FoodOptionResponse> foodOptionResponseList, HashMap<String, Long> idMealTypeMap) {
        this.foodOptionResponseList = foodOptionResponseList;
        this.idMealTypeMap = idMealTypeMap;
    }
}
