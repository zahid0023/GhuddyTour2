package com.ghuddy.backendapp.tours.model.entities.booking;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.combination.AvailableTourPackageOptionEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tour_package_option_booking")
public class TourPackageOptionBookingEntity extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "sales_order_id", nullable = false)
    private SalesOrderEntity salesOrderEntity;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "available_option_id", nullable = false)
    private AvailableTourPackageOptionEntity availableOptionEntity;

    @NotNull
    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @NotNull
    @Column(name = "payable_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal payableAmount;

    @Size(max = 100)
    @NotNull
    @Column(name = "status", nullable = false, length = 100)
    private String status;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;


    public TourPackageOptionBookingEntity(AvailableTourPackageOptionEntity availableTourPackageOptionEntity, SalesOrderEntity salesOrderEntity, Integer quantity) {
        this.availableOptionEntity = availableTourPackageOptionEntity;
        this.salesOrderEntity = salesOrderEntity;
        this.quantity = quantity;
        this.totalAmount = availableTourPackageOptionEntity.getGhuddyWebsiteBlackPricePerPerson().multiply(BigDecimal.valueOf(quantity));
        this.payableAmount = availableTourPackageOptionEntity.getGhuddyWebsiteRedPricePerPerson().multiply(BigDecimal.valueOf(quantity));
        this.status = "INITIATED";
    }

}