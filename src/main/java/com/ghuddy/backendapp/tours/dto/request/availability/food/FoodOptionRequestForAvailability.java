package com.ghuddy.backendapp.tours.dto.request.availability.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.commons.OptionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class FoodOptionRequestForAvailability {
    @Schema(description = "Meal Package available in day", required = true, example = "1")
    @JsonProperty("day_number")
    private Integer dayNumber;

    @JsonProperty("tour_package_meal_type_wise_meal_packages")
    private HashMap<Integer, List<MealPackageRequestForAvailability>> mealTypeWiseMealPackages;
}
