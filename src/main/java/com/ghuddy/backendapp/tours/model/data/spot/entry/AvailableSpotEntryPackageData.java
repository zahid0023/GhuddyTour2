package com.ghuddy.backendapp.tours.model.data.spot.entry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.activity.ActivityData;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.AvailableSpotEntryPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AvailableSpotEntryPackageData {
    @Schema(description = "The activity for which spot entry is required")
    @JsonProperty("activity")
    private ActivityData activityData;
    @Schema(description = "The remark")
    @JsonProperty("remark")
    private String remark;
    @Schema(description = "The spot entry price per person")
    @JsonProperty("spot_entry_price_per_person")
    private BigDecimal spotEntryPricePerPerson;
    public AvailableSpotEntryPackageData(AvailableSpotEntryPackageEntity availableSpotEntryPackageEntity){
        this.activityData = new ActivityData(availableSpotEntryPackageEntity.getSpotEntryPackageEntity().getActivityEntity());
        this.remark = availableSpotEntryPackageEntity.getSpotEntryPackageEntity().getRemark();
        this.spotEntryPricePerPerson = availableSpotEntryPackageEntity.getSpotEntryPricePerPerson();
    }
}
