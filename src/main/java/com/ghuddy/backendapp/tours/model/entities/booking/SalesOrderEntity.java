package com.ghuddy.backendapp.tours.model.entities.booking;

import com.ghuddy.backendapp.model.UserEntity;
import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.booking.InvoiceEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "sales_order")
public class SalesOrderEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Size(max = 50)
    @Column(name = "order_code", length = 50)
    private String orderCode;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;

    //@OneToOne(mappedBy = "salesOrderEntity", cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    //private InvoiceEntity invoiceEntity;

    @OneToMany(mappedBy = "salesOrderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourPackageOptionBookingEntity> tourPackageOptionBookingEntities = new ArrayList<>();

}