package com.ghuddy.backendapp.tours.es.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.model.entities.ESAccommodationOptionDocument;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

@Data
public class ESAccommodationOptionData  {
    @Schema(description = "The available accommodation option id that traveller can choose from")
    @JsonProperty("tour_package_available_accommodation_option_id")
    private Long availableAccommodationOptionId;
    @JsonProperty("accommodation_packages")
    @Field(name = "accommodation_packages")
    private List<ESAccommodationPackageData> accommodationPackageList;

    public ESAccommodationOptionData(ESAccommodationOptionDocument esAccommodationOptionDocument) {
        this.availableAccommodationOptionId = esAccommodationOptionDocument.getAvailableAccommodationOptionId();
        this.accommodationPackageList = esAccommodationOptionDocument.getAccommodationPackageList().stream()
                .map(esAccommodationPackageDocument -> new ESAccommodationPackageData(esAccommodationPackageDocument))
                .toList();
    }
}
