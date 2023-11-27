package com.ghuddy.backendapp.tours.es.model.entities;

import com.ghuddy.backendapp.tours.enums.TripType;
import com.ghuddy.backendapp.tours.model.entities.transportation.AvailableTransportationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.TransportationPackageEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
public class ESTransportationPackageDocument {
    @Field(name = "tour_package_available_transportation_package_id", type = FieldType.Long)
    private Long availableTransportationPackageId;
    @Field(name = "tour_package_available_transportation_route_name", type = FieldType.Text)
    private String transportationRouteName;
    @Field(name = "tour_package_available_transportation_mode_name", type = FieldType.Text)
    private String transportationModeName;
    @Field(name = "tour_package_available_transportation_brand_name", type = FieldType.Text)
    private String transportationBrandName;
    @Field(name = "tour_package_available_transportation_provide_name", type = FieldType.Text)
    private String transportationProviderName;
    @Field(name = "trip_type", type = FieldType.Keyword)
    private TripType tripType;
    @Field(name = "is_ac", type = FieldType.Boolean)
    private boolean isAc;

    public ESTransportationPackageDocument(AvailableTransportationPackageEntity availableTransportationPackageEntity) {
        TransportationPackageEntity transportationPackageEntity = availableTransportationPackageEntity.getTransportationPackageEntity();
        this.availableTransportationPackageId = availableTransportationPackageEntity.getId();
        this.transportationRouteName = transportationPackageEntity.getTransportationRouteEntity().getSourceLocation().getPlaceName() + " - " + transportationPackageEntity.getTransportationRouteEntity().getDestinationLocation().getPlaceName();
        this.transportationModeName = transportationPackageEntity.getTransportationModeEntity().getModeName();
        this.transportationBrandName = transportationPackageEntity.getTransportationBrandEntity().getBrandName();
        this.transportationProviderName = transportationPackageEntity.getTransportationProviderEntity().getTransportationProviderName();
        this.tripType = transportationPackageEntity.getTripType();
        this.isAc = transportationPackageEntity.getIsAc();
    }
}
