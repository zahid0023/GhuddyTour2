package com.ghuddy.backendapp.tours.es.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.model.entities.ESFoodItemDocument;
import lombok.Data;

@Data
public class ESFoodItemData {
    @JsonProperty("food_item_name")
    private String foodItemName;

    public ESFoodItemData(ESFoodItemDocument esFoodItemDocument) {
        this.foodItemName = esFoodItemDocument.getFoodItemName();
    }
}
