package com.ghuddy.backendapp.tours.es.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.enums.TripType;
import com.ghuddy.backendapp.tours.es.model.entities.ESTransferPackageDocument;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
public class ESTransferPackageData {
    @JsonProperty("tour_package_available_transfer_package_id")
    @Field(name = "tour_package_available_transfer_package_id")
    private Long availableTransferPackageId;

    @JsonProperty("tour_package_available_transfer_route")
    @Field(name = "tour_package_available_transfer_route")
    private String transferRoute;
    @JsonProperty("tour_package_available_transportation_mode_name")
    @Field(name = "tour_package_available_transportation_mode_name")
    private String transportationModeName;
    @JsonProperty("tour_package_available_transportation_provider_name")
    @Field(name = "tour_package_available_transportation_provider_name")
    private String transportationProviderName;
    @JsonProperty("is_ac")
    @Field(name = "is_ac")
    private Boolean isAc;
    @JsonProperty("trip_type")
    @Field(name = "trip_type")
    private TripType tripType;
    @JsonProperty("suitable_for_persons")
    @Field(name = "suitable_for_persons")
    private Integer suitableForPersons;

    public ESTransferPackageData(ESTransferPackageDocument esTransferPackageDocument) {
        this.availableTransferPackageId = esTransferPackageDocument.getAvailableTransferPackageId();
        this.transferRoute = esTransferPackageDocument.getTransferRoute();
        this.transportationModeName = esTransferPackageDocument.getTransportationModeName();
        this.transportationProviderName = esTransferPackageDocument.getTransportationProviderName();
        this.isAc = esTransferPackageDocument.getIsAc();
        this.tripType = esTransferPackageDocument.getTripType();
        this.suitableForPersons = esTransferPackageDocument.getSuitableForPersons();
    }
}
