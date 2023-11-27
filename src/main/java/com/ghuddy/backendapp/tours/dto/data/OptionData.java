package com.ghuddy.backendapp.tours.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OptionData {
    @JsonProperty("tour_package_option_price_per_person")
    private BigDecimal totalOptionPricePerPerson;
    @JsonProperty("tour_package_option_is_active")
    private Boolean isActive;
}
