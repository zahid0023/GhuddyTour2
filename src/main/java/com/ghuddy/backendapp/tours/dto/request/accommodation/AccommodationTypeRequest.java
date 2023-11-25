package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AccommodationTypeRequest {
    @Schema(description = "The name of the accommodation type", required = true, example = "Hotel")
    @JsonProperty("accommodation_type_name")
    private String accommodationTypeName;
}
