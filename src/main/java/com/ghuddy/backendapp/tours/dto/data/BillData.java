package com.ghuddy.backendapp.tours.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BillData {
    @JsonProperty("products")
    List<ProductMeta> purchasedProducts;
    @JsonProperty("total_amount")
    BigDecimal totalBlackPrice;
    @JsonProperty("total_payable_amount")
    BigDecimal totalRedPrice;
    @JsonProperty("total_discount_amount")
    BigDecimal totalDiscountAmount;

    public BillData(List<ProductMeta> purchasedProducts, BigDecimal totalBlackPrice, BigDecimal totalRedPrice) {
        this.purchasedProducts = purchasedProducts;
        this.totalBlackPrice = totalBlackPrice;
        this.totalRedPrice = totalRedPrice;
        this.totalDiscountAmount = totalBlackPrice.subtract(totalRedPrice);
    }
}
