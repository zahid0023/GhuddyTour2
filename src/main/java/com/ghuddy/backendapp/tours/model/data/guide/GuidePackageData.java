package com.ghuddy.backendapp.tours.model.data.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.guide.GuidePackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GuidePackageData {
    @Schema(description = "The guide package id")
    @JsonProperty("tour_package_guide_package_id")
    private Long guidePackageId;
    @Schema(description = "The number of guides for day")
    @JsonProperty("number_of_guides_for_day")
    private Integer numberOfGuidesForDay;
    @Schema(description = "The total guide price for day")
    @JsonProperty("total_guide_price_for_day")
    private BigDecimal totalGuidePriceForDay;

    public GuidePackageData(GuidePackageEntity guidePackageEntity) {
        this.guidePackageId = guidePackageEntity.getId();
        this.numberOfGuidesForDay = guidePackageEntity.getNumberOfGuideForDay();
        this.totalGuidePriceForDay = guidePackageEntity.getTotalGuidePriceForDay();
    }
}
