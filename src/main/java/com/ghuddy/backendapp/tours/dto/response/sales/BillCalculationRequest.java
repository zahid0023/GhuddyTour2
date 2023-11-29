package com.ghuddy.backendapp.tours.dto.response.sales;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import com.ghuddy.backendapp.tours.dto.request.sales.TourPackageOptionAddToCart;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class BillCalculationRequest extends BaseRequest {
    @Schema(description = "The tour cart")
    @JsonProperty("tour_cart")
    private List<TourPackageOptionAddToCart> tourPackageOptionAddToCartList;

    @Override
    public void validate() throws AbstractException {
        
    }
}
