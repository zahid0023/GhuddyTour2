package com.ghuddy.backendapp.tours.es.model.entities;

import com.ghuddy.backendapp.tours.model.entities.tour.TourSpecialityEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
public class ESTourSpecialityDocument {
    @Field(name = "tour_speciality_id", type = FieldType.Long)
    private Long tourSpecialityId;
    @Field(name = "tour_speciality_title", type = FieldType.Text)
    private String tourSpecialityTitle;
    @Field(name = "tour_speciality_description", type = FieldType.Text)
    private String tourSpecialityDescription;
    @Field(name = "tour_speciality_icon_url", type = FieldType.Text)
    private String tourSpecialityIconUrl;

    public ESTourSpecialityDocument(TourSpecialityEntity tourSpecialityEntity) {
        this.tourSpecialityId = tourSpecialityEntity.getId();
        this.tourSpecialityTitle = tourSpecialityEntity.getTitle();
        this.tourSpecialityDescription = tourSpecialityEntity.getDescription();
        this.tourSpecialityIconUrl = tourSpecialityEntity.getIconUrl();
    }
}
