package com.ghuddy.backendapp.tours.dto.request.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MealPackageAddRequest extends BaseRequest {
    @Schema(description = "The subscribed tour id",required = true,example = "1")
    @JsonProperty("subscribed_tour_id")
    private Long subscribedTourId;
    @Schema(description = "The meal package",required = true)
    @JsonProperty("tour_meal_package")
    private MealPackageRequest mealPackageRequest;

    /**
     * @throws AbstractException
     */
    @Override
    public void validate() throws AbstractException {

    }
}
