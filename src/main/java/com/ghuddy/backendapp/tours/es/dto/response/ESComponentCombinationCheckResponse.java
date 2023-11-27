package com.ghuddy.backendapp.tours.es.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ESComponentCombinationCheckResponse {
    @JsonProperty("black_price")
    private BigDecimal ghuddyWebSiteBlackPrice;
    @JsonProperty("red_price")
    private BigDecimal ghuddyWebSiteRedPrice;

    public ESComponentCombinationCheckResponse(BigDecimal ghuddyWebSiteBlackPrice, BigDecimal ghuddyWebSiteRedPrice) {
        this.ghuddyWebSiteBlackPrice = ghuddyWebSiteBlackPrice;
        this.ghuddyWebSiteRedPrice = ghuddyWebSiteRedPrice;
    }
}
