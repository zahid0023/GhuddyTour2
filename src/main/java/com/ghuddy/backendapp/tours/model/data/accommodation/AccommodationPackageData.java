package com.ghuddy.backendapp.tours.model.data.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AccommodationPackageData {
    @Schema(description = "The package id for this accommodation when saved in the db, otherwise null")
    @JsonProperty("tour_package_accommodation_package_id")
    private Long accommodationPackageId;
    @Schema(description = "The accommodation id")
    @JsonProperty("tour_package_accommodation_id")
    private Long accommodationId;
    @Schema(description = "The name of the accommodation associated with this tour package accommodation package")
    @JsonProperty("tour_package_accommodation_name")
    private String tourPackageAccommodationName;
    @Schema(description = "The room category id")
    @JsonProperty("tour_package_room_category_id")
    private Long roomCategoryId;
    @Schema(description = "The name of the room category associated with this tour package accommodation package")
    @JsonProperty("tour_package_room_category_name")
    private String tourPackageRoomCategoryName;
    @Schema(description = "The room type id")
    @JsonProperty("tour_package_room_type_id")
    private Long roomTypeId;
    @Schema(description = "The name of the room type associated with this tour package accommodation package")
    @JsonProperty("tour_package_room_type_name")
    private String tourPackageRoomTypeName;
    @Schema(description = "Whether this room is shareable or not")
    @JsonProperty("is_room_shareable")
    private boolean isShareable;
    @Schema(description = "How many people can stay in this room")
    @JsonProperty("suitable_for_persons")
    private Integer suitableForPersons;
    @Schema(description = "Total number of beds in this room")
    @JsonProperty("bed_count")
    private Integer bedCount;
    @Schema(description = "The type of bed provided with this room")
    @JsonProperty("bed_configuration")
    private String bedConfiguration;

    @Schema(description = "The price of this accommodation package", required = true, example = "1200")
    @JsonProperty("per_night_room_price")
    private BigDecimal unitPrice;

    public AccommodationPackageData(AccommodationPackageEntity accommodationPackageEntity) {
        this.accommodationPackageId = accommodationPackageEntity.getId();
        this.accommodationId = accommodationPackageEntity.getTourAccommodationEntity().getId();
        this.tourPackageAccommodationName = accommodationPackageEntity.getTourAccommodationEntity().getAccommodationName();
        this.roomCategoryId = accommodationPackageEntity.getTourRoomCategoryEntity().getId();
        this.tourPackageRoomCategoryName = accommodationPackageEntity.getTourRoomCategoryEntity().getRoomCategoryName();
        this.roomTypeId = accommodationPackageEntity.getTourRoomTypeEntity().getId();
        this.tourPackageRoomTypeName = accommodationPackageEntity.getTourRoomTypeEntity().getRoomTypeName();
        this.isShareable = accommodationPackageEntity.getIsShareable();
        this.suitableForPersons = accommodationPackageEntity.getSuitableForPersons();
        this.bedCount = accommodationPackageEntity.getBedCount();
        this.bedConfiguration = accommodationPackageEntity.getBedConfiguration();
        this.unitPrice = accommodationPackageEntity.getPerNightRoomPrice();
    }
}
