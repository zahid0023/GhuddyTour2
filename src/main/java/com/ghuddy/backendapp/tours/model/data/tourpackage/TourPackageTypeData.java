package com.ghuddy.backendapp.tours.model.data.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageTypeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TourPackageTypeData {
    @Schema(description = "The id of the tour package type", example = "1")
    @JsonProperty("tour_package_type_id")
    private Long tourPackageTypeId;
    @Schema(description = "The name of the tour package type", example = "Couple Package")
    @JsonProperty("tour_package_type_name")
    private String tourPackageTypeName;
    @Schema(description = "The description about the tour package type", example = "A short description about tour package type")
    @JsonProperty("tour_package_type_description")
    private String tourPackageTypeDescription;
    @Schema(description = "The max number of persons this tour package is suitable for", example = "2")
    @JsonProperty("tour_package_type_suitable_for")
    private Integer tourPackageTypeSuitableFor;

    public TourPackageTypeData(TourPackageTypeEntity tourPackageTypeEntity) {
        this.tourPackageTypeId = tourPackageTypeEntity.getId();
        this.tourPackageTypeName = tourPackageTypeEntity.getPackageTypeName();
        this.tourPackageTypeDescription = tourPackageTypeEntity.getDescription();
        this.tourPackageTypeSuitableFor = tourPackageTypeEntity.getSuitableFor();
    }
}
