package com.ghuddy.backendapp.tours.es.dto.data;

import lombok.Data;

import java.util.Map;

@Data
public class ESProductionMeta {
    private String configId;
    private String title;
    private String description;
    private Double unitBlackPrice;
    private Double unitRedPrice;
    private Long quantity;
    private Double totalBlackPrice;
    private Double totalRedPrice;
    private String productType;
}
