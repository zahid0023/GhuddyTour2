package com.ghuddy.backendapp.tours.es.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.model.entities.ESMealPackageDocument;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
public class ESMealPackageData {
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
    private List<ESFoodItemData> esFoodItemDataList;

    public ESMealPackageData(ESMealPackageDocument esMealPackageDocument) {
        this.mealTypeId = esMealPackageDocument.getMealTypeId();
        this.mealTypeName = esMealPackageDocument.getMealTypeName();
        this.dayNumber = esMealPackageDocument.getDayNumber();
        this.esFoodItemDataList = esMealPackageDocument.getEsFoodItemDocumentList().stream()
                .map(esFoodItemDocument -> new ESFoodItemData(esFoodItemDocument))
                .toList();
    }
}
