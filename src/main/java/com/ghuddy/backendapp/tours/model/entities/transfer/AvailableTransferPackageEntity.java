package com.ghuddy.backendapp.tours.model.entities.transfer;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "available_transfer_packages")
public class AvailableTransferPackageEntity extends BaseEntity {
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "available_transfer_option_id", nullable = false)
    private AvailableTransferOptionEntity availableTransferOptionEntity;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "transfer_package_id", nullable = false)
    private TransferPackageEntity transferPackageEntity;
    @NotNull
    @Column(name = "per_vehicle_per_trip_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal perVehiclePerTripPrice;

}