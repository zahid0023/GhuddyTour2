package com.ghuddy.backendapp.tours.dto.request.availability.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.commons.OptionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class GuideOptionRequestForAvailability {
    @Schema(description = "The guide package packages",required = true)
    @JsonProperty("tour_package_guide_packages")
    private List<GuidePackageRequestForAvailability> guidePackageRequestForAvailabilityList;
}
