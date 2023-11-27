package com.ghuddy.backendapp.tours.model.data.food;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.data.OptionData;
import com.ghuddy.backendapp.tours.model.entities.food.AvailableFoodOptionEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AvailableFoodOptionData extends OptionData {
    @JsonProperty("tour_package_available_meal_packages")
    private List<AvailableMealPackageData> availableMealPackageDataList;

    public AvailableFoodOptionData(AvailableFoodOptionEntity availableFoodOptionEntity){
        this.availableMealPackageDataList = availableFoodOptionEntity.getAvailabilityGeneratedMealPackageEntities().stream()
                .map(availableMealPackageEntity -> new AvailableMealPackageData(availableMealPackageEntity))
                .toList();
        this.setTotalOptionPricePerPerson(availableFoodOptionEntity.getTotalOptionPricePerPerson());
        this.setIsActive(availableFoodOptionEntity.getIsActive());
    }
}
