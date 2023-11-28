package com.ghuddy.backendapp.tours.es.model.entities;

import com.ghuddy.backendapp.tours.model.entities.spot.entry.AvailableSpotEntryPackageEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ESSpotEntryPackageDocument {
    @Field(name = "activity_name", type = FieldType.Text)
    private String activityName;
    @Field(name = "activity_images", type = FieldType.Nested)
    private List<ESImageDocument> esImageDocumentList;
    @Field(name = "price_per_person", type = FieldType.Double)
    private BigDecimal pricePerPerson;
    @Field(name = "remark", type = FieldType.Text)
    private String remark;

    public ESSpotEntryPackageDocument(AvailableSpotEntryPackageEntity availableSpotEntryPackageEntity){
        this.activityName = availableSpotEntryPackageEntity.getSpotEntryPackageEntity().getActivityEntity().getActivityName();
        this.esImageDocumentList = availableSpotEntryPackageEntity.getSpotEntryPackageEntity().getActivityEntity().getActivityImageEntities().stream()
                .map(activityImageEntity -> new ESImageDocument(activityImageEntity))
                .toList();
        this.pricePerPerson = availableSpotEntryPackageEntity.getSpotEntryPricePerPerson();
        this.remark = availableSpotEntryPackageEntity.getSpotEntryPackageEntity().getRemark();
    }

}
