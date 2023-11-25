package com.ghuddy.backendapp.tours.dto.request.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class MealTypeListAddRequest extends BaseRequest {
    @Schema(description = "The list of the meal types that will be stored in the database",required = true)
    @JsonProperty("meal_types")
    private List<MealTypeRequest> mealTypes;

    @Override
    public void validate() throws AbstractException {

    }
}
