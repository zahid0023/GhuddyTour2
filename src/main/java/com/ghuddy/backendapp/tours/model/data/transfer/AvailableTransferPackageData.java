package com.ghuddy.backendapp.tours.model.data.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.enums.TripType;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailableTransferPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.TransferPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AvailableTransferPackageData {
    @Schema(description = "The route where the transfer service will be provided")
    @JsonProperty("tour_package_transfer_route")
    private String transferRoute;
    @Schema(description = "The name of the transportation mode")
    @JsonProperty("tour_package_transportation_mode_name")
    private String transportationModeName;
    @Schema(description = "The name of the transportation provider")
    @JsonProperty("tour_package_transportation_provider_name")
    private String transportationProviderName;
    @Schema(description = "Where is ac")
    @JsonProperty("is_ac")
    private Boolean isAc;
    @Schema(description = "The type of the trip")
    @JsonProperty("trip_type")
    private TripType tripType;
    @Schema(description = "per day price of the transportation")
    @JsonProperty("per_vehicle_per_trip_price")
    private BigDecimal unitPrice;
    @Schema(description = "maximum number of travellers in a vehicle")
    @JsonProperty("suitable_for_persons")
    private Integer suitableForPersons;
    public AvailableTransferPackageData(AvailableTransferPackageEntity availableTransferPackageEntity){
        TransferPackageEntity transferPackageEntity = availableTransferPackageEntity.getTransferPackageEntity();
        this.transferRoute = transferPackageEntity.getTransferRoute();
        this.transportationModeName = transferPackageEntity.getTransportationModeEntity().getModeName();
        this.transportationProviderName = transferPackageEntity.getTransportationProviderEntity().getTransportationProviderName();
        this.isAc = transferPackageEntity.getIsAc();
        this.tripType = transferPackageEntity.getTripType();
        this.unitPrice = availableTransferPackageEntity.getPerVehiclePerTripPrice();
        this.suitableForPersons = transferPackageEntity.getSuitableForPersons();
    }
}
