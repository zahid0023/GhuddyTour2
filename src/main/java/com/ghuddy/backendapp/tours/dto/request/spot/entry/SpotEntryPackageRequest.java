package com.ghuddy.backendapp.tours.dto.request.spot.entry;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpotEntryPackageRequest {
    @Schema(description = "The activity id that belongs to this activity entry", example = "1", required = true)
    @JsonProperty("activity_id")
    private Long activityId;
    @Schema(description = "The per person sport entry price", example = "500.00", required = true)
    @JsonProperty("spot_entry_price_per_person")
    private BigDecimal pricePerPerson;

    @Schema(description = "not sure still", example = "FOR_ADULT", required = true)
    @JsonProperty("remark")
    private String remark;
}
