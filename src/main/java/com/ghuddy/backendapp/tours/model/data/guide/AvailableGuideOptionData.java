package com.ghuddy.backendapp.tours.model.data.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.data.OptionData;
import com.ghuddy.backendapp.tours.model.entities.guide.AvailableGuideOptionEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AvailableGuideOptionData extends OptionData {
    @JsonProperty("tour_package_available_guide_packages")
    private List<AvailableGuidePackageData> availableGuidePackageDataList;

    public AvailableGuideOptionData(AvailableGuideOptionEntity availableGuideOptionEntity){
        this.availableGuidePackageDataList = availableGuideOptionEntity.getAvailableGuidePackageEntityList().stream()
                .map(availableGuidePackageEntity -> new AvailableGuidePackageData(availableGuidePackageEntity))
                .toList();
        this.setTotalOptionPricePerPerson(availableGuideOptionEntity.getTotalOptionPricePerPerson());
        this.setIsActive(availableGuideOptionEntity.getIsActive());
    }
}
