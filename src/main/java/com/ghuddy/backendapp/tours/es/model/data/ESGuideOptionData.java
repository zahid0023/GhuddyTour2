package com.ghuddy.backendapp.tours.es.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.model.entities.ESGuideOptionDocument;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

@Data
public class ESGuideOptionData {
    @Schema(description = "The available accommodation option id that traveller can choose from")
    @JsonProperty("tour_package_available_guide_option_id")
    private Long availableGuideOptionId;
    @JsonProperty("guide_packages")
    @Field(name = "guide_packages")
    private List<ESGuidePackageData> esGuidePackageDataList;

    public ESGuideOptionData(ESGuideOptionDocument esGuideOptionDocument){
        this.availableGuideOptionId = esGuideOptionDocument.getAvailableGuideOptionId();
        this.esGuidePackageDataList = esGuideOptionDocument.getEsGuidePackageDocumentList().stream()
                .map(esGuidePackageDocument -> new ESGuidePackageData(esGuidePackageDocument))
                .toList();
    }
}
