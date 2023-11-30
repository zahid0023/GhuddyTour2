package com.ghuddy.backendapp.tours.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourComponentOptionCombinationDocument;
import com.ghuddy.backendapp.tours.model.entities.combination.AvailableTourPackageOptionEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Data
public class ProductMeta implements Serializable {
    @JsonProperty("config_id")
    private String configId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("product_type")
    private String productType;
    @JsonProperty("unit_black_price")
    private BigDecimal unitBlackPrice;
    @JsonProperty("unit_red_price")
    private BigDecimal unitRedPrice;
    @JsonProperty("quantity")
    private Long quantity;
    @JsonProperty("total_black_price")
    private BigDecimal totalBlackPrice;
    @JsonProperty("total_red_price")
    private BigDecimal totalRedPrice;
    @JsonProperty("custom_fields")
    private Map<String, String> customFields;

    public ProductMeta(ESTourComponentOptionCombinationDocument esTourComponentOptionCombinationDocument, Integer quantity) {
        this.configId = esTourComponentOptionCombinationDocument.getConfigId();
        this.title = esTourComponentOptionCombinationDocument.getTitle();
        this.description = "description";
        this.productType = "TOUR";
        this.unitBlackPrice = esTourComponentOptionCombinationDocument.getGhuddyWebsiteBlackPrice();
        this.unitRedPrice = esTourComponentOptionCombinationDocument.getGhuddyWebsiteRedPrice();
        this.quantity = Long.valueOf(quantity);
        this.totalBlackPrice = unitBlackPrice.multiply(BigDecimal.valueOf(quantity));
        this.totalRedPrice = unitRedPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public ProductMeta(AvailableTourPackageOptionEntity availableTourPackageOptionEntity, Integer quantity){
        this.configId = String.valueOf(UUID.randomUUID());
        this.title = "title";
        this.description = "description";
        this.productType = "TOUR";
        this.unitBlackPrice = availableTourPackageOptionEntity.getGhuddyWebsiteBlackPricePerPerson();
        this.unitRedPrice = availableTourPackageOptionEntity.getGhuddyWebsiteRedPricePerPerson();
        this.quantity = Long.valueOf(quantity);
        this.totalBlackPrice = unitBlackPrice.multiply(BigDecimal.valueOf(quantity));
        this.totalRedPrice = unitBlackPrice.multiply(BigDecimal.valueOf(quantity));
    }
}

