package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoomCategoryRequest{
    @Schema(description = "The name of the room category", required = true, example = "Standard")
    @JsonProperty("room_category_name")
    private String roomCategoryName;
    @Schema(description = "A short description of the room category", example = "A short description")
    @JsonProperty("description")
    private String description;
}
