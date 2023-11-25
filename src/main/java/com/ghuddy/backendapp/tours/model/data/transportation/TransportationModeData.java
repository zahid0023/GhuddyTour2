package com.ghuddy.backendapp.tours.model.data.transportation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.transportation.TransportationModeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransportationModeData {
    @Schema(description = "The id of the transportation mode", example = "1")
    @JsonProperty("transportation_mode_id")
    private Long transportationModeId;
    @Schema(description = "The name of the transportation mode", example = "Car")
    @JsonProperty("transportation_mode_name")
    private String transportationModeName;
    @Schema(description = "The description of the transportation mode")
    @JsonProperty("transportation_mode_description")
    private String transportationModeDescription;
    @Schema(description = "The icon url of the transportation mode")
    @JsonProperty("transportation_mode_icon_url")
    private String transportationModeIconUrl;

    public TransportationModeData(TransportationModeEntity transportationModeEntity) {
        this.transportationModeId = transportationModeEntity.getId();
        this.transportationModeName = transportationModeEntity.getModeName();
        this.transportationModeDescription = transportationModeEntity.getDescription();
        this.transportationModeIconUrl = transportationModeEntity.getIconUrl();
    }
}
