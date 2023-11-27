package com.ghuddy.backendapp.tours.es.model.data;

import com.ghuddy.backendapp.tours.es.model.entities.ESTourSpecialityDocument;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
public class ESTourSpecialityData {
    @Field(name = "tour_speciality_id", type = FieldType.Long)
    private Long tourSpecialityId;
    @Field(name = "tour_speciality_title", type = FieldType.Text)
    private String tourSpecialityTitle;
    @Field(name = "tour_speciality_description", type = FieldType.Text)
    private String tourSpecialityDescription;
    @Field(name = "tour_speciality_icon_url", type = FieldType.Text)
    private String tourSpecialityIconUrl;

    public ESTourSpecialityData(ESTourSpecialityDocument esTourSpecialityDocument) {
        this.tourSpecialityId = esTourSpecialityDocument.getTourSpecialityId();
        this.tourSpecialityTitle = esTourSpecialityDocument.getTourSpecialityTitle();
        this.tourSpecialityDescription = esTourSpecialityDocument.getTourSpecialityDescription();
        this.tourSpecialityIconUrl = esTourSpecialityDocument.getTourSpecialityIconUrl();
    }
}
