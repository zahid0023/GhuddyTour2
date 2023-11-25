package com.ghuddy.backendapp.model;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.AddedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.TransportationRouteEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "place_near_by")
public class DestinationLocationEntity extends BaseEntity{
    @Column(name = "place_name", nullable = false, unique = true)
    private String placeName;

    // ghuddy backend code base have to add the following
    @OneToMany(mappedBy = "destinationLocationEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddedTourEntity> tourLocationEntities = new LinkedList<>();

    @OneToMany(mappedBy = "sourceLocation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransportationRouteEntity> transportationSourceRoutes = new LinkedList<>();

    @OneToMany(mappedBy = "destinationLocation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransportationRouteEntity> transportationDestinationRoutes = new LinkedList<>();
}
