package com.ghuddy.backendapp.tours.model.entities.transportation;

import com.ghuddy.backendapp.model.DestinationLocationEntity;
import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "transportation_routes")
public class TransportationRouteEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "source_location_id", nullable = false)
    private DestinationLocationEntity sourceLocation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "destination_location_id", nullable = false)
    private DestinationLocationEntity destinationLocation;

    @OneToMany(mappedBy = "transportationRouteEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransportationPackageEntity> tourPackageTransportationEntities = new ArrayList<>();

}