package com.ghuddy.backendapp.tours.es.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.food.AvailableMealPackageEntity;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
public class ESMealPackageDocument {

    @Field(name = "meal_type_id", type = FieldType.Long)
    @JsonProperty("meal_type_id")
    private Long mealTypeId;

    @Field(name = "meal_type_name", type = FieldType.Text)
    @JsonProperty("meal_type_name")
    private String mealTypeName;

    @Field(name = "day_number", type = FieldType.Integer)
    @JsonProperty("day_number")
    private Integer dayNumber;

    @Field(name = "food_items", type = FieldType.Nested, includeInParent = true)
    @JsonProperty("food_items")
    private List<ESFoodItemDocument> esFoodItemDocumentList;

    public ESMealPackageDocument(AvailableMealPackageEntity availableMealPackageEntity) {
        this.mealTypeId = availableMealPackageEntity.getMealPackageEntity().getMealTypeEntity().getId();
        this.mealTypeName = availableMealPackageEntity.getMealPackageEntity().getMealTypeEntity().getMealTypeName();
        this.esFoodItemDocumentList = availableMealPackageEntity.getMealPackageEntity().getFoodItemEntities().stream()
                .map(foodItemEntity -> new ESFoodItemDocument(foodItemEntity))
                .toList();
        this.dayNumber = availableMealPackageEntity.getMealPackageAvailableInDay();
    }
}
