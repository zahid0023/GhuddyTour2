package com.ghuddy.backendapp.tours.es.model.entities;

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
public class ESTransferOptionDocument {

    @Field(name = "tour_package_available_transfer_option_id", type = FieldType.Long)
    private Long availableTransferOptionId;

    @Field(name = "tour_package_available_transfer_packages", type = FieldType.Nested, includeInParent = true)
    private List<ESTransferPackageDocument> transferPackageList;

    public ESTransferOptionDocument(AvailableTransferOptionEntity availableTransferOptionEntity) {
        availableTransferOptionId = availableTransferOptionEntity.getId();
        this.transferPackageList = availableTransferOptionEntity.getAvailableTransferPackageEntityList().stream()
                .map(availableTransferPackageEntity -> new ESTransferPackageDocument(availableTransferPackageEntity))
                .toList();
    }
}
