package com.ghuddy.backendapp.tours.model.data.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.data.OptionData;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailableAccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailableAccommodationPackageEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Data
public class AvailableAccommodationOptionData extends OptionData {
    @JsonProperty("tour_package_available_accommodation_packages")
    private List<AvailableAccommodationPackageData> availableAccommodationPackageDataList;

    public AvailableAccommodationOptionData(AvailableAccommodationOptionEntity availableAccommodationOptionEntity){
        this.availableAccommodationPackageDataList = availableAccommodationOptionEntity.getAvailableAccommodationPackageEntityList().stream()
                .map(availableAccommodationPackageEntity -> new AvailableAccommodationPackageData(availableAccommodationPackageEntity))
                .toList();
        this.setTotalOptionPricePerPerson(availableAccommodationOptionEntity.getTotalOptionPricePerPerson());
        this.setIsActive(availableAccommodationOptionEntity.getIsActive());
    }
}
