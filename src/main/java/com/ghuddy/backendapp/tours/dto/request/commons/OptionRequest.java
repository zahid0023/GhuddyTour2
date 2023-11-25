package com.ghuddy.backendapp.tours.dto.request.commons;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OptionRequest {
    @Schema(description = "Whether this option is active or not", required = true, example = "true")
    @JsonProperty("tour_package_option_is_active")
    private Boolean isActive;
}
