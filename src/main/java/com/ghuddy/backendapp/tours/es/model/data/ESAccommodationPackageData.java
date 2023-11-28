package com.ghuddy.backendapp.tours.es.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.model.entities.ESAccommodationPackageDocument;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@NoArgsConstructor
public class ESAccommodationPackageData {
    @Field("tour_package_available_accommodation_package_id")
    @JsonProperty("tour_package_available_accommodation_package_id")
    private Long availableAccommodationPackageId;

    @JsonProperty("tour_package_available_accommodation_name")
    @Field(name = "tour_package_available_accommodation_name")
    private String tourPackageAccommodationName;
    @JsonProperty("tour_package_available_room_category_name")
    @Field(name = "tour_package_available_room_category_name")
    private String tourPackageRoomCategoryName;
    @JsonProperty("tour_package_available_room_type_name")
    @Field(name = "tour_package_available_room_type_name")
    private String tourPackageRoomTypeName;
    @JsonProperty("is_room_shareable")
    @Field(name = "is_room_shareable")
    private boolean isShareable;
    @JsonProperty("suitable_for_persons")
    @Field(name = "suitable_for_persons")
    private Integer suitableForPersons;
    @JsonProperty("bed_count")
    @Field(name = "bed_count")
    private Integer bedCount;
    @JsonProperty("bed_configuration")
    @Field(name = "bed_configuration")
    private String bedConfiguration;
    @JsonProperty("night_number")
    @Field(name = "night_number")
    private Integer nightNumber;

    public ESAccommodationPackageData(ESAccommodationPackageDocument esAccommodationPackageDocument) {
        this.availableAccommodationPackageId = esAccommodationPackageDocument.getAvailableAccommodationPackageId();
        this.tourPackageAccommodationName = esAccommodationPackageDocument.getTourPackageAccommodationName();
        this.tourPackageRoomCategoryName = esAccommodationPackageDocument.getTourPackageRoomCategoryName();
        this.tourPackageRoomTypeName = esAccommodationPackageDocument.getTourPackageRoomTypeName();
        this.isShareable = esAccommodationPackageDocument.isShareable();
        this.suitableForPersons = esAccommodationPackageDocument.getSuitableForPersons();
        this.bedCount = esAccommodationPackageDocument.getBedCount();
        this.bedConfiguration = esAccommodationPackageDocument.getBedConfiguration();
        this.nightNumber = esAccommodationPackageDocument.getNightNumber();
    }
}
