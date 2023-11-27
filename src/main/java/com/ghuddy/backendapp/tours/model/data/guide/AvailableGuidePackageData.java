package com.ghuddy.backendapp.tours.model.data.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.guide.AvailableGuidePackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AvailableGuidePackageData {
    @Schema(description = "The number of guides for day")
    @JsonProperty("number_of_guides_for_day")
    private Integer numberOfGuidesForDay;
    @Schema(description = "The total guide price for day")
    @JsonProperty("total_guide_price_for_day")
    private BigDecimal totalGuidePriceForDay;

    public AvailableGuidePackageData(AvailableGuidePackageEntity availableGuidePackageEntity){
        this.numberOfGuidesForDay = availableGuidePackageEntity.getGuidePackageEntity().getNumberOfGuideForDay();
        this.totalGuidePriceForDay = availableGuidePackageEntity.getTotalGuidePackagePrice();
    }
}
