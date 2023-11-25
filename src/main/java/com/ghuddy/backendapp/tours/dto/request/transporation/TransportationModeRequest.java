package com.ghuddy.backendapp.tours.dto.request.transporation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TransportationModeRequest{
    @Schema(description = "The name of the mode of the transportation", required = true, example = "Bus")
    @JsonProperty("mode_name")
    private String modeName;
    @Schema(description = "The description of the mode of the description", example = "A short description")
    @JsonProperty("description")
    private String description;
    @Schema(description = "The icon url of the mode that will be presented in the UI", required = true)
    @JsonProperty("mode_icon_url")
    private String iconUrl;
}
