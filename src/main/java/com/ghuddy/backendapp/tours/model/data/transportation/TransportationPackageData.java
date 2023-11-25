package com.ghuddy.backendapp.tours.model.data.transportation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.enums.TripType;
import com.ghuddy.backendapp.tours.model.entities.transportation.TransportationPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransportationPackageData {
    @Schema(description = "The transportation package id")
    @JsonProperty("tour_package_transportation_package_id")
    private Long transportationPackageId;

    @Schema(description = "The transportation route id")
    @JsonProperty("tour_package_transportation_route_id")
    private Long transportationRouteId;
    @Schema(description = "The name of the route associated with this tour package transportation package")
    @JsonProperty("tour_package_transportation_route_name")
    private String transportationRouteName;
    @Schema(description = "The transportation mode id")
    @JsonProperty("tour_package_transportation_mode_id")
    private Long transportationModeId;
    @Schema(description = "The name of the transportation mode associated with this tour package transportation package")
    @JsonProperty("tour_package_transportation_mode_name")
    private String transportationModeName;
    @Schema(description = "The transportation brand id")
    @JsonProperty("tour_package_transportation_brand_id")
    private Long transportationBrandId;
    @Schema(description = "The name of the transportation brand associated with this tour package transportation package")
    @JsonProperty("tour_package_transportation_brand_name")
    private String transportationBrandName;
    @Schema(description = "The transportation provider id")
    @JsonProperty("tour_package_transportation_provider_id")
    private Long transportationProviderId;
    @Schema(description = "The name of the transportation provider associated with this tour package transportation package")
    @JsonProperty("tour_package_transportation_provider_name")
    private String transportationProviderName;
    @Schema(description = "Is the trip round or one way")
    @JsonProperty("trip_type")
    private TripType tripType;
    @Schema(description = "Whether ac or not")
    @JsonProperty("is_ac")
    private boolean isAc;

    @Schema(description = "The price of this transportation package", required = true, example = "120")
    @JsonProperty("transportation_package_unit_price")
    private BigDecimal unitPrice;

    @Schema(description = "Whether is active")
    @JsonProperty("transportation_package_is_active")
    private Boolean isActive;

    public TransportationPackageData(TransportationPackageEntity transportationPackageEntity) {
        this.transportationPackageId = transportationPackageEntity.getId();
        this.transportationRouteId = transportationPackageEntity.getTransportationRouteEntity().getId();
        this.transportationRouteName = transportationPackageEntity.getTransportationRouteEntity().getSourceLocation().getPlaceName() + " - " + transportationPackageEntity.getTransportationRouteEntity().getDestinationLocation().getPlaceName();
        this.transportationModeId = transportationPackageEntity.getTransportationModeEntity().getId();
        this.transportationModeName = transportationPackageEntity.getTransportationModeEntity().getModeName();
        this.transportationBrandId = transportationPackageEntity.getTransportationBrandEntity().getId();
        this.transportationBrandName = transportationPackageEntity.getTransportationBrandEntity().getBrandName();
        this.transportationProviderId = transportationPackageEntity.getTransportationProviderEntity().getId();
        this.transportationProviderName = transportationPackageEntity.getTransportationProviderEntity().getTransportationProviderName();
        this.tripType = transportationPackageEntity.getTripType();
        this.isAc = transportationPackageEntity.getIsAc();
        this.unitPrice = transportationPackageEntity.getPerPersonTransportationPackagePrice();
    }
}
