package com.ghuddy.backendapp.tours.model.entities.spot.entry;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "available_spot_entry_packages")
public class AvailableSpotEntryPackageEntity extends BaseEntity {
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "available_spot_entry_option_id", nullable = false)
    private AvailableSpotEntryOptionEntity availableSpotEntryOptionEntity;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "spot_entry_package_id", nullable = false)
    private SpotEntryPackageEntity spotEntryPackageEntity;
    @NotNull
    @Column(name = "spot_entry_price_per_person", nullable = false, precision = 10, scale = 2)
    private BigDecimal spotEntryPricePerPerson;
}