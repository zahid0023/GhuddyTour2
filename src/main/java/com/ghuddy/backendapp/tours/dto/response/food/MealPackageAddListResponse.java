package com.ghuddy.backendapp.tours.dto.response.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.food.MealPackageData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MealPackageAddListResponse extends BaseResponse {
    @Schema(description = "The meal packages")
    @JsonProperty("tour_package_meal_packages")
    Map<Long, List<MealPackageData>> mealPackages;

    public MealPackageAddListResponse(Map<Long, List<MealPackageData>> mealPackages, String requestId) {
        this.mealPackages = mealPackages;
        this.setRequestId(requestId);
    }
}
