package com.ghuddy.backendapp.tours.model.entities.accommodation;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "available_accommodation_packages")
public class AvailableAccommodationPackageEntity extends BaseEntity {
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "available_accommodation_option_id", nullable = false)
    private AvailableAccommodationOptionEntity availableAccommodationOptionEntity;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "accommodation_package_id", nullable = false)
    private AccommodationPackageEntity accommodationPackageEntity;
    @NotNull
    @Column(name = "per_night_room_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal accommodationPackagePrice;
    @NotNull
    @Column(name = "night_number", nullable = false)
    private Integer nightNumber;

}