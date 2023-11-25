package com.ghuddy.backendapp.tours.dto.request.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MealTypeAddRequest extends BaseRequest {
    @Schema(description = "The meal type that will be stored in the database", required = true)
    @JsonProperty("meal_type")
    private MealTypeRequest mealType;

    @Override
    public void validate() throws AbstractException {

    }
}
