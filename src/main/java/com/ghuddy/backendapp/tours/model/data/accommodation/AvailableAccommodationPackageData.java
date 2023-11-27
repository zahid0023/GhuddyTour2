package com.ghuddy.backendapp.tours.model.data.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailableAccommodationPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
public class AvailableAccommodationPackageData {

    @Schema(description = "The name of the accommodation associated with this tour package accommodation package")
    @JsonProperty("tour_package_accommodation_name")
    private String tourPackageAccommodationName;
    @Schema(description = "The name of the room category associated with this tour package accommodation package")
    @JsonProperty("tour_package_room_category_name")
    private String tourPackageRoomCategoryName;
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
    @Schema(description = "The price of this accommodation package")
    @JsonProperty("per_night_room_price")
    private BigDecimal unitPrice;
    @Schema(description = "The night number")
    @JsonProperty("night_number")
    private Integer nightNumber;

    public AvailableAccommodationPackageData(AvailableAccommodationPackageEntity availableAccommodationPackageEntity){
        AccommodationPackageEntity accommodationPackageEntity = availableAccommodationPackageEntity.getAccommodationPackageEntity();
        this.tourPackageAccommodationName = accommodationPackageEntity.getTourAccommodationEntity().getAccommodationName();
        this.tourPackageRoomCategoryName = accommodationPackageEntity.getTourRoomCategoryEntity().getRoomCategoryName();
        this.tourPackageRoomTypeName = accommodationPackageEntity.getTourRoomTypeEntity().getRoomTypeName();
        this.isShareable = accommodationPackageEntity.getIsShareable();
        this.suitableForPersons = accommodationPackageEntity.getSuitableForPersons();
        this.bedCount = accommodationPackageEntity.getBedCount();
        this.bedConfiguration = accommodationPackageEntity.getBedConfiguration();
        this.unitPrice = accommodationPackageEntity.getPerNightRoomPrice();
        this.nightNumber = availableAccommodationPackageEntity.getNightNumber();
    }
}
