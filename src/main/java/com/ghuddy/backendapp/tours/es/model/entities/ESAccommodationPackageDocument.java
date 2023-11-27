package com.ghuddy.backendapp.tours.es.model.entities;

import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailableAccommodationPackageEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
public class ESAccommodationPackageDocument {
    @Field(value = "tour_package_available_accommodation_package_id", type = FieldType.Long)
    private Long availableAccommodationPackageId;
    @Field(name = "tour_package_available_accommodation_name", type = FieldType.Text)
    private String tourPackageAccommodationName;
    @Field(name = "tour_package_available_room_category_name", type = FieldType.Text)
    private String tourPackageRoomCategoryName;
    @Field(name = "tour_package_available_room_type_name", type = FieldType.Text)
    private String tourPackageRoomTypeName;
    @Field(name = "is_room_shareable", type = FieldType.Boolean)
    private boolean isShareable;
    @Field(name = "suitable_for_persons", type = FieldType.Integer)
    private Integer suitableForPersons;
    @Field(name = "bed_count", type = FieldType.Integer)
    private Integer bedCount;
    @Field(name = "bed_configuration", type = FieldType.Text)
    private String bedConfiguration;
    @Field(name = "night_number", type = FieldType.Integer)
    private Integer nightNumber;

    public ESAccommodationPackageDocument(AvailableAccommodationPackageEntity availableAccommodationPackageEntity) {
        AccommodationPackageEntity accommodationPackageEntity = availableAccommodationPackageEntity.getAccommodationPackageEntity();
        this.availableAccommodationPackageId = availableAccommodationPackageEntity.getId();
        this.tourPackageAccommodationName = accommodationPackageEntity.getTourAccommodationEntity().getAccommodationName();
        this.tourPackageRoomCategoryName = accommodationPackageEntity.getTourRoomCategoryEntity().getRoomCategoryName();
        this.tourPackageRoomTypeName = accommodationPackageEntity.getTourRoomTypeEntity().getRoomTypeName();
        this.isShareable = accommodationPackageEntity.getIsShareable();
        this.suitableForPersons = accommodationPackageEntity.getSuitableForPersons();
        this.bedCount = accommodationPackageEntity.getBedCount();
        this.bedConfiguration = accommodationPackageEntity.getBedConfiguration();
        this.nightNumber = availableAccommodationPackageEntity.getNightNumber();
    }
}
