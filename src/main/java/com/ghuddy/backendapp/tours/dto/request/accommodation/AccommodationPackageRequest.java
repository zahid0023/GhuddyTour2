package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccommodationPackageRequest {
    @Schema(description = "The ID of the accommodation", required = true, example = "1")
    @JsonProperty("accommodation_id")
    private Long accommodationID;
    @Schema(description = "The ID of the room category", required = true, example = "1")
    @JsonProperty("room_category_id")
    private Long roomCategoryID;
    @Schema(description = "The ID of the room type", required = true, example = "1")
    @JsonProperty("room_type_id")
    private Long roomTypeID;
    @Schema(description = "The price of this accommodation package", required = true, example = "1200")
    @JsonProperty("per_night_room_price")
    private BigDecimal perNightRoomPrice; // per night room price

    @Schema(description = "Number of person that will stay in this room", required = true, example = "2")
    @JsonProperty("suitable_for_persons")
    private Integer forPersons;
    @Schema(description = "Whether the traveler may share this room with another fellow traveller", required = true, example = "true")
    @JsonProperty("is_shareable")
    private Boolean isShareable;

    @Schema(description = "Number of beds in the room", required = true, example = "1")
    @JsonProperty("number_of_beds")
    private Integer bedCount;
    @Schema(description = "The type of bed provided in this room", required = false, example = "Queen")
    @JsonProperty("bed_configuration")
    private String bedConfiguration;

}
