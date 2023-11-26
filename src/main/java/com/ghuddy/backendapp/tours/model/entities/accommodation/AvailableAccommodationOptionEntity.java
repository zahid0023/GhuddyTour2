package com.ghuddy.backendapp.tours.model.entities.accommodation;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.AvailableComponentsAllOptionsCombinationEntity;
import com.ghuddy.backendapp.tours.model.entities.combination.AvailableComponentsInclusiveOptionsCombinationEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailableTourPackageEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "available_accommodation_options")
public class AvailableAccommodationOptionEntity extends BaseEntity {
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "available_tour_package_id", nullable = false)
    private AvailableTourPackageEntity availableTourPackageEntity;
    @OneToMany(mappedBy = "availableAccommodationOptionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableAccommodationPackageEntity> availableAccommodationPackageEntityList = new LinkedList<>();
    @NotNull
    @Column(name = "total_option_price_per_person", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalOptionPricePerPerson;
    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "availableAccommodationOptionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableComponentsInclusiveOptionsCombinationEntity> availableComponentsInclusiveOptionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "availableAccommodationOptionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableComponentsAllOptionsCombinationEntity> availableComponentsAllOptionsCombinationEntities = new ArrayList<>();

}