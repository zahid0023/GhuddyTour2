package com.ghuddy.backendapp.tours.dto.data;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class BillData {
    List<ProductMeta> purchasedProducts;
    BigDecimal totalAmount;
    BigDecimal totalPayAbleBeforeCoupon;
    BigDecimal totalPayableAfterCoupon;

    public BillData(ProductMeta purchasedProduct, BigDecimal totalAmount, BigDecimal totalPayAbleBeforeCoupon) {
        this.purchasedProducts = new LinkedList<>();
        this.purchasedProducts.add(purchasedProduct);
        this.totalAmount = totalAmount;
        this.totalPayAbleBeforeCoupon = totalPayAbleBeforeCoupon;
    }

    public BillData(List<ProductMeta> purchasedProducts, BigDecimal totalAmount, BigDecimal totalPayAbleBeforeCoupon) {
        this.purchasedProducts = new LinkedList<>();
        this.purchasedProducts.addAll(purchasedProducts);
        this.totalAmount = totalAmount;
        this.totalPayAbleBeforeCoupon = totalPayAbleBeforeCoupon;
    }

    public BillData() {
        this.totalAmount = BigDecimal.ZERO;
        this.totalPayAbleBeforeCoupon = BigDecimal.ZERO;
        this.purchasedProducts = new LinkedList<>();
    }
}
