package com.ghuddy.backendapp.tours.model.entities.tourpackage;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.combination.AvailableComponentsAllOptionsCombinationEntity;
import com.ghuddy.backendapp.tours.model.entities.combination.AvailableComponentsInclusiveOptionsCombinationEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailableAccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.AvailableFoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.AvailableGuideOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.AvailableSpotEntryOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailableTransferOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.AvailableTransportationPackageEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "available_tour_packages")
public class AvailableTourPackageEntity extends BaseEntity {
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "subscribed_tour_id", nullable = false)
    private SubscribedTourEntity subscribedTourEntity;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_package_type_id", nullable = false)
    private TourPackageTypeEntity tourPackageTypeEntity;

    @NotNull
    @Column(name = "tour_start_date", nullable = false)
    private LocalDate tourStartDate;

    @NotNull
    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @NotNull
    @Column(name = "bookable_seats", nullable = false)
    private Integer bookableSeats;

    @NotNull
    @Column(name = "is_accommodation_inclusive", nullable = false)
    private Boolean isAccommodationInclusive = false;

    @NotNull
    @Column(name = "is_food_inclusive", nullable = false)
    private Boolean isFoodInclusive = false;

    @NotNull
    @Column(name = "is_transfer_inclusive", nullable = false)
    private Boolean isTransferInclusive = false;

    @NotNull
    @Column(name = "is_guide_inclusive", nullable = false)
    private Boolean isGuideInclusive = false;

    @NotNull
    @Column(name = "is_spot_entry_inclusive", nullable = false)
    private Boolean isSpotEntryInclusive = false;

    @Size(max = 50)
    @NotNull
    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Size(max = 255)
    @Column(name = "tour_package_name")
    private String tourPackageName;

    @Column(name = "tour_package_description", columnDefinition = "text")
    private String tourPackageDescription;

    @OneToMany(mappedBy = "availableTourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableAccommodationOptionEntity> availableAccommodationOptionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "availableTourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableFoodOptionEntity> availableFoodOptionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "availableTourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableTransferOptionEntity> availableTransferOptionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "availableTourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableTransportationPackageEntity> availableTransportationPackageEntities = new ArrayList<>();

    @OneToMany(mappedBy = "availableTourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableGuideOptionEntity> availableGuideOptionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "availableTourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableSpotEntryOptionEntity> availableSpotEntryOptionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "availableTourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableComponentsInclusiveOptionsCombinationEntity> availableComponentsInclusiveOptionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "availableTourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableComponentsAllOptionsCombinationEntity> availableComponentsAllOptionsCombinationEntities = new ArrayList<>();

}