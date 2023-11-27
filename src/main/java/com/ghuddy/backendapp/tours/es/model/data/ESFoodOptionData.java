package com.ghuddy.backendapp.tours.es.model.data;

import com.ghuddy.backendapp.tours.es.model.entities.ESAccommodationOptionDocument;
import com.ghuddy.backendapp.tours.es.model.entities.ESAccommodationPackageDocument;
import com.ghuddy.backendapp.tours.es.model.entities.ESFoodOptionDocument;
import com.ghuddy.backendapp.tours.es.model.entities.ESMealPackageDocument;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
public class ESFoodOptionData {
    @Field(name = "tour_package_available_food_option_id", type = FieldType.Long)
    private Long availableFoodOptionId;
    @Field(name = "tour_package_available_food_packages", type = FieldType.Nested, includeInParent = true)
    private List<ESMealPackageData> esMealPackageDataList;

    public ESFoodOptionData(ESFoodOptionDocument esFoodOptionDocument){
        this.availableFoodOptionId = esFoodOptionDocument.getAvailableFoodOptionId();
        this.esMealPackageDataList = esFoodOptionDocument.getMealPackageDocumentList().stream()
                .map(esMealPackageDocument -> new ESMealPackageData(esMealPackageDocument))
                .toList();
    }
}
