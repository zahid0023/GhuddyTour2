package com.ghuddy.backendapp.tours.model.entities.transportation;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.AvailableComponentsAllOptionsCombinationEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailableTourPackageEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "available_transportation_packages")
public class AvailableTransportationPackageEntity extends BaseEntity {
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "available_tour_package_id",nullable = false)
    private AvailableTourPackageEntity availableTourPackageEntity;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "transportation_package_id",nullable = false)
    private TransportationPackageEntity transportationPackageEntity;
    @NotNull
    @Column(name = "transportation_package_price_per_person", nullable = false, precision = 10, scale = 2)
    private BigDecimal transportationPackagePrice;
    @NotNull
    @Column(name = "is_active",nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "availableTransportationPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableComponentsAllOptionsCombinationEntity> availableComponentsAllOptionsCombinationEntities = new ArrayList<>();

}