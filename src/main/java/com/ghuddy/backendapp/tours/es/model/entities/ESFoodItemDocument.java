package com.ghuddy.backendapp.tours.es.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.food.FoodItemEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class ESFoodItemDocument {

    @Field(name = "food_item_name", type = FieldType.Text)
    @JsonProperty("food_item_name")
    private String foodItemName;

    public ESFoodItemDocument(FoodItemEntity foodItemEntity) {
        this.foodItemName = foodItemEntity.getFoodItemName();
    }
}
