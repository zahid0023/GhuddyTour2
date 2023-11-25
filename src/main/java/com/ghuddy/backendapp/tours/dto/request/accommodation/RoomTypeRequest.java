package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoomTypeRequest{
    @Schema(description = "The name of the room type", required = true, example = "Couple")
    @JsonProperty("room_type_name")
    private String roomTypeName;
    @Schema(description = "A short description of the room type", required = false, example = "A short description")
    @JsonProperty("description")
    private String description;
}
