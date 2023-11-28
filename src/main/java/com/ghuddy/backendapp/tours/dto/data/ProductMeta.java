package com.ghuddy.backendapp.tours.dto.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class ProductMeta implements Serializable {
    private String configId;
    private String title;
    private String description;
    private String productType;
    private Double unitBlackPrice;
    private Double unitRedPrice;
    private Long quantity;
    private Double totalBlackPrice;
    private Double totalRedPrice;

    private Map<String, String> customFields;
}

