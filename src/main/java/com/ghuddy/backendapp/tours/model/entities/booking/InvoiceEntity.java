package com.ghuddy.backendapp.tours.model.entities.booking;

import com.fasterxml.jackson.databind.JsonNode;
import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "invoice")
public class InvoiceEntity extends BaseEntity {
    @Size(max = 50)
    @Column(name = "invoice_status", length = 50)
    private String invoiceStatus;

    @Size(max = 255)
    @Column(name = "booking_person_name")
    private String bookingPersonName;

    @Size(max = 255)
    @Column(name = "booking_person_email")
    private String bookingPersonEmail;

    @Size(max = 255)
    @Column(name = "booking_person_phone_number")
    private String bookingPersonPhoneNumber;

    @Size(max = 255)
    @Column(name = "currency")
    private String currency;

    @Column(name = "product_list")
    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType")
    private JsonNode productList;

    @Column(name = "due_amount")
    private Double dueAmount;

    @Column(name = "paid_amount")
    private Double paidAmount;

    @Column(name = "discount_percent")
    private Double discountPercent;

    @Column(name = "total_payable")
    private Double totalPayable;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "discount_amount")
    private Double discountAmount;

    @Size(max = 50)
    @Column(name = "invoice_nr", length = 50)
    private String invoiceNr;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "invoice_date")
    private LocalDate invoiceDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "sales_order_id")
    private SalesOrderEntity salesOrder;

    @Column(name = "total_payable_before_coupon")
    private Double totalPayableBeforeCoupon;

    @Column(name = "coupon_discount_amount")
    private Double couponDiscountAmount;

    @Column(name = "redeemed_coupons")
    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType")
    private JsonNode redeemedCoupons;

    @Column(name = "invoice_url")
    @Type(type = "org.hibernate.type.TextType")
    private String invoiceUrl;


    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "sales_order_id", nullable = false, insertable = false, updatable = false)
    private SalesOrderEntity salesOrderEntity;

}