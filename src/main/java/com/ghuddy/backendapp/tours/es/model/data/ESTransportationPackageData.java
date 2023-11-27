package com.ghuddy.backendapp.tours.es.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.enums.TripType;
import com.ghuddy.backendapp.tours.es.model.entities.ESTransportationPackageDocument;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
public class ESTransportationPackageData {
    @JsonProperty("tour_package_available_transportation_package_id")
    @Field(name = "tour_package_available_transportation_package_id")
    private Long availableTransportationPackageId;
    @JsonProperty("tour_package_available_transportation_route_name")
    @Field(name = "tour_package_available_transportation_route_name")
    private String transportationRouteName;
    @JsonProperty("tour_package_available_transportation_mode_name")
    @Field(name = "tour_package_available_transportation_mode_name")
    private String transportationModeName;
    @JsonProperty("tour_package_available_transportation_brand_name")
    @Field(name = "tour_package_available_transportation_brand_name")
    private String transportationBrandName;
    @JsonProperty("tour_package_available_transportation_provider_name")
    @Field(name = "tour_package_available_transportation_provide_name")
    private String transportationProviderName;
    @JsonProperty("trip_type")
    @Field(name = "trip_type")
    private TripType tripType;
    @JsonProperty("is_ac")
    @Field(name = "is_ac")
    private boolean isAc;

    public ESTransportationPackageData(ESTransportationPackageDocument esTransportationPackageDocument) {
        this.availableTransportationPackageId = esTransportationPackageDocument.getAvailableTransportationPackageId();
        this.transportationRouteName = esTransportationPackageDocument.getTransportationRouteName();
        this.transportationModeName = esTransportationPackageDocument.getTransportationModeName();
        this.transportationBrandName = esTransportationPackageDocument.getTransportationBrandName();
        this.transportationProviderName = esTransportationPackageDocument.getTransportationProviderName();
        this.tripType = esTransportationPackageDocument.getTripType();
        this.isAc = esTransportationPackageDocument.isAc();
    }
}
