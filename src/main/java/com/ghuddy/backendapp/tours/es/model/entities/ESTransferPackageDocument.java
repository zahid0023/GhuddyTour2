package com.ghuddy.backendapp.tours.es.model.entities;

import com.ghuddy.backendapp.tours.enums.TripType;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailableTransferPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.TransferPackageEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
public class ESTransferPackageDocument {
    @Field(name = "tour_package_available_transfer_package_id", type = FieldType.Long)
    private Long availableTransferPackageId;
    @Field(name = "tour_package_available_transfer_route", type = FieldType.Keyword)
    private String transferRoute;
    @Field(name = "tour_package_available_transportation_mode_name", type = FieldType.Text)
    private String transportationModeName;
    @Field(name = "tour_package_available_transportation_provider_name", type = FieldType.Text)
    private String transportationProviderName;
    @Field(name = "is_ac", type = FieldType.Boolean)
    private Boolean isAc;
    @Field(name = "trip_type", type = FieldType.Keyword)
    private TripType tripType;
    @Field(name = "suitable_for_persons", type = FieldType.Integer)
    private Integer suitableForPersons;

    public ESTransferPackageDocument(AvailableTransferPackageEntity availableTransferPackageEntity) {
        TransferPackageEntity transferPackageEntity = availableTransferPackageEntity.getTransferPackageEntity();
        this.availableTransferPackageId = availableTransferPackageEntity.getId();
        this.transferRoute = transferPackageEntity.getTransferRoute();
        this.transportationModeName = transferPackageEntity.getTransportationModeEntity().getModeName();
        this.transportationProviderName = transferPackageEntity.getTransportationProviderEntity().getTransportationProviderName();
        this.isAc = transferPackageEntity.getIsAc();
        this.tripType = transferPackageEntity.getTripType();
        this.suitableForPersons = transferPackageEntity.getSuitableForPersons();
    }
}
