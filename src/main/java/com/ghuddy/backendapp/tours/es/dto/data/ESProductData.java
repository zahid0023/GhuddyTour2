package com.ghuddy.backendapp.tours.es.dto.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ESProductData {
    private String title; // tour name
    private String description; // tour package name with options
    private Long quantity; // number of tour packages for same options combination./
    private Double perQuantityPriceBefDiscount;
    private Double perQuantityPriceAftDiscount;
    private String configId;
    private Double totalBlackPrice;
    private Double totalRedPrice;
    private String productType;
    private Map<String, String> customFields;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ESProductData ESProductDataData = (ESProductData) o;
        return Objects.equals(title, ESProductDataData.title)
                && Objects.equals(description, ESProductDataData.description)
                && Objects.equals(quantity, ESProductDataData.quantity)
                && Objects.equals(perQuantityPriceBefDiscount, ESProductDataData.perQuantityPriceBefDiscount)
                && Objects.equals(perQuantityPriceAftDiscount, ESProductDataData.perQuantityPriceAftDiscount)
                && Objects.equals(configId, ESProductDataData.configId)
                && Objects.equals(totalBlackPrice, ESProductDataData.totalBlackPrice)
                && Objects.equals(totalRedPrice, ESProductDataData.totalRedPrice);
    }

    @Override
    public int hashCode() {
        return title.hashCode()
                + description.hashCode()
                + quantity.hashCode()
                + perQuantityPriceBefDiscount.hashCode()
                + perQuantityPriceAftDiscount.hashCode()
                + configId.hashCode()
                + totalBlackPrice.hashCode()
                + totalRedPrice.hashCode();
    }
}
