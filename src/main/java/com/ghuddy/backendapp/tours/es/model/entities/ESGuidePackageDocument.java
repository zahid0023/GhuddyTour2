package com.ghuddy.backendapp.tours.es.model.entities;

import com.ghuddy.backendapp.tours.model.entities.guide.AvailableGuidePackageEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
@Getter
@Setter
@NoArgsConstructor
public class ESGuidePackageDocument {
    @Field(name = "number_of_guides_for_day", type = FieldType.Integer)
    private Integer numberOfGuidesForDay;

    @Field(name = "day_number", type = FieldType.Integer)
    private Integer dayNumber;

    public ESGuidePackageDocument(AvailableGuidePackageEntity availableGuidePackageEntity){
        this. numberOfGuidesForDay = availableGuidePackageEntity.getGuidePackageEntity().getNumberOfGuideForDay();
        this.dayNumber = availableGuidePackageEntity.getDayNumber();
    }
}
