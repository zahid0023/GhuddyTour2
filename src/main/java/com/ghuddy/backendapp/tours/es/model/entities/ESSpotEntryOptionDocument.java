package com.ghuddy.backendapp.tours.es.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.model.data.ESAccommodationPackageData;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.AvailableSpotEntryOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ESSpotEntryOptionDocument {
    @Field(name = "tour_package_available_spot_entry_option_id", type = FieldType.Long)
    private Long availableSpotEntryOptionId;

    @Field(name = "spot_entry_packages", type = FieldType.Nested)
    private List<ESSpotEntryPackageDocument> esSpotEntryPackageDocumentList;

    public ESSpotEntryOptionDocument(AvailableSpotEntryOptionEntity availableSpotEntryOptionEntity){
        this.availableSpotEntryOptionId = availableSpotEntryOptionEntity.getId();
        this.esSpotEntryPackageDocumentList = availableSpotEntryOptionEntity.getAvailableSpotEntryPackageEntityList().stream()
                .map(availableSpotEntryPackageEntity -> new ESSpotEntryPackageDocument(availableSpotEntryPackageEntity))
                .toList();
    }
}
