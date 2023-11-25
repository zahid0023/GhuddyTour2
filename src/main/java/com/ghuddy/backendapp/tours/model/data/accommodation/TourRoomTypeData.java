package com.ghuddy.backendapp.tours.model.data.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.accommodation.TourRoomTypeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TourRoomTypeData {
    @Schema(description = "The id of the tour room type", example = "1")
    @JsonProperty("tour_room_type_id")
    private Long tourRoomTypeId;
    @Schema(description = "The name of the tour room type", example = "Couple Room")
    @JsonProperty("tour_room_type_name")
    private String tourRoomTypeName;
    @Schema(description = "The description of the tour room type", example = "A description of tour room type")
    @JsonProperty("tour_room_type_description")
    private String tourRoomTypeDescription;

    public TourRoomTypeData(TourRoomTypeEntity tourRoomTypeEntity) {
        this.tourRoomTypeId = tourRoomTypeEntity.getId();
        this.tourRoomTypeName = tourRoomTypeEntity.getRoomTypeName();
        this.tourRoomTypeDescription = tourRoomTypeEntity.getDescription();
    }
}
