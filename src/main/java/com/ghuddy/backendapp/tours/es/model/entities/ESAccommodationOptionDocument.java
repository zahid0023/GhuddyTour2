package com.ghuddy.backendapp.tours.es.model.entities;

import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailableAccommodationOptionEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ESAccommodationOptionDocument {
    @Field(name = "tour_package_available_accommodation_option_id", type = FieldType.Long)
    private Long availableAccommodationOptionId;
    @Field(name = "tour_package_available_accommodation_packages", type = FieldType.Nested, includeInParent = true)
    private List<ESAccommodationPackageDocument> accommodationPackageList;

    public ESAccommodationOptionDocument(AvailableAccommodationOptionEntity availableAccommodationOptionEntity) {
        this.availableAccommodationOptionId = availableAccommodationOptionEntity.getId();
        this.accommodationPackageList = availableAccommodationOptionEntity.getAvailableAccommodationPackageEntityList().stream()
                .map(availableAccommodationPackageEntity -> new ESAccommodationPackageDocument(availableAccommodationPackageEntity))
                .collect(Collectors.toList());
    }
}
