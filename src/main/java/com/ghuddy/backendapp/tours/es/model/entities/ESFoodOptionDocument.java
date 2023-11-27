package com.ghuddy.backendapp.tours.es.model.entities;

import com.ghuddy.backendapp.tours.es.model.data.ESAccommodationPackageData;
import com.ghuddy.backendapp.tours.model.entities.food.AvailableFoodOptionEntity;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
public class ESFoodOptionDocument {
    @Field(name = "tour_package_available_food_option_id", type = FieldType.Long)
    private Long availableFoodOptionId;
    @Field(name = "tour_package_available_meal_packages", type = FieldType.Nested, includeInParent = true)
    private List<ESMealPackageDocument> mealPackageDocumentList;

    public ESFoodOptionDocument(AvailableFoodOptionEntity availableFoodOptionEntity) {
        this.availableFoodOptionId = availableFoodOptionEntity.getId();
        this.mealPackageDocumentList = availableFoodOptionEntity.getAvailabilityGeneratedMealPackageEntities().stream()
                .map(availableMealPackageEntity -> new ESMealPackageDocument(availableMealPackageEntity))
                .toList();
    }
}
