package com.ghuddy.backendapp.tours.dto.request.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GuidePackageRequest {
    @Schema(description = "The number of days the guide service will be provided", required = true, example = "[1,2]")
    @JsonProperty("day_number")
    private Integer dayNumber;
    @Schema(description = "The number of guide provided for this guide package")
    @JsonProperty("number_of_guides_for_day")
    private Integer numberOfGuidesForDay;
    @Schema(description = "The price of the guide per day", required = true, example = "700")
    @JsonProperty("tour_guide_price_per_day")
    private BigDecimal perDayGuidePrice;

}
