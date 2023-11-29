package com.ghuddy.backendapp.tours.model.entities.guide;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.combination.AvailableComponentsAllOptionsCombinationEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailableTourPackageEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "available_guide_options")
public class AvailableGuideOptionEntity extends BaseEntity {
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "available_tour_package_id", nullable = false)
    private AvailableTourPackageEntity availableTourPackageEntity;
    @OneToMany(mappedBy = "availableGuideOptionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableGuidePackageEntity> availableGuidePackageEntityList = new ArrayList<>();
    @NotNull
    @Column(name = "total_option_price_per_person", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalOptionPricePerPerson;
    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "availableGuideOptionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableComponentsAllOptionsCombinationEntity> availableComponentsAllOptionsCombinationEntities = new ArrayList<>();

}