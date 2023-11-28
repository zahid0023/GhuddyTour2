package com.ghuddy.backendapp.tours.es.model.entities;

import com.ghuddy.backendapp.tours.model.entities.guide.AvailableGuideOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailableTransferOptionEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ESGuideOptionDocument {
    @Field(name = "tour_package_available_guide_option_id", type = FieldType.Long)
    private Long availableGuideOptionId;
    @Field(name = "tour_package_available_guide_packages", type = FieldType.Nested, includeInParent = true)
    private List<ESGuidePackageDocument> esGuidePackageDocumentList;

    public ESGuideOptionDocument(AvailableGuideOptionEntity availableGuideOptionEntity) {
        availableGuideOptionId= availableGuideOptionEntity.getId();
        this.esGuidePackageDocumentList = availableGuideOptionEntity.getAvailableGuidePackageEntityList().stream()
                .map(availableGuidePackageEntity -> new ESGuidePackageDocument(availableGuidePackageEntity))
                .toList();
    }
}
