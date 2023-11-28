package com.ghuddy.backendapp.tours.es.model.data;

import com.ghuddy.backendapp.tours.es.model.entities.ESImageDocument;
import com.ghuddy.backendapp.tours.es.model.entities.ESSpotEntryPackageDocument;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ESSpotEntryPackageData {
    @Field(name = "activity_name", type = FieldType.Text)
    private String activityName;
    @Field(name = "activity_images", type = FieldType.Nested)
    private List<ESImageData> esImageDataList;
    @Field(name = "price_per_person", type = FieldType.Double)
    private BigDecimal pricePerPerson;
    @Field(name = "remark", type = FieldType.Text)
    private String remark;
    public ESSpotEntryPackageData(ESSpotEntryPackageDocument esSpotEntryPackageDocument){
        this.activityName = esSpotEntryPackageDocument.getActivityName();
        this.esImageDataList = esSpotEntryPackageDocument.getEsImageDocumentList().stream()
                .map(esImageDocument -> new ESImageData(esImageDocument))
                .toList();
        this.pricePerPerson = esSpotEntryPackageDocument.getPricePerPerson();
        this.remark = esSpotEntryPackageDocument.getRemark();
    }
}
