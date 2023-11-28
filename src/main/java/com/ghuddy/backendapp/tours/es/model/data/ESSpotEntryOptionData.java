package com.ghuddy.backendapp.tours.es.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.model.entities.ESSpotEntryOptionDocument;
import lombok.Data;

import java.util.List;

@Data
public class ESSpotEntryOptionData {
    @JsonProperty("tour_package_available_spot_entry_option_id")
    private Long availableSpotEntryOptionId;

    @JsonProperty("spot_entry_packages")
    private List<ESSpotEntryPackageData> esSpotEntryPackageDataList;

    public ESSpotEntryOptionData(ESSpotEntryOptionDocument esSpotEntryOptionDocument){
        this.availableSpotEntryOptionId = esSpotEntryOptionDocument.getAvailableSpotEntryOptionId();
        this.esSpotEntryPackageDataList = esSpotEntryOptionDocument.getEsSpotEntryPackageDocumentList().stream()
                .map(esSpotEntryPackageDocument -> new ESSpotEntryPackageData(esSpotEntryPackageDocument))
                .toList();
    }
}
