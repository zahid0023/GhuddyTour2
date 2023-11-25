package com.ghuddy.backendapp.tours.model.data.spot.entry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.activity.ActivityData;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpotEntryPackageData {
    @Schema(description = "The id")
    @JsonProperty("spot_entry_package_id")
    private Long spotEntryPackageId;
    @Schema(description = "The activity for which spot entry is required")
    @JsonProperty("activity")
    private ActivityData activityData;
    @Schema(description = "The remark")
    @JsonProperty("remark")
    private String remark;
    @Schema(description = "The spot entry price per person")
    @JsonProperty("spot_entry_price_per_person")
    private BigDecimal spotEntryPricePerPerson;

    public SpotEntryPackageData(SpotEntryPackageEntity spotEntryPackageEntity) {
        this.spotEntryPackageId = spotEntryPackageEntity.getId();
        this.activityData = new ActivityData(spotEntryPackageEntity.getActivityEntity());
        this.remark = spotEntryPackageEntity.getRemark();
        this.spotEntryPricePerPerson = spotEntryPackageEntity.getPricePerPerson();
    }
}
