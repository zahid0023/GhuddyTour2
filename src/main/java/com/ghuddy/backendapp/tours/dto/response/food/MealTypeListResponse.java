package com.ghuddy.backendapp.tours.dto.response.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.food.MealTypeData;
import lombok.Data;

import java.util.List;

@Data
public class MealTypeListResponse {
    @JsonProperty("meal_types")
    private List<MealTypeData> mealTypeDataList;

    public MealTypeListResponse(List<MealTypeData> mealTypeDataList) {
        this.mealTypeDataList = mealTypeDataList;
    }
}
