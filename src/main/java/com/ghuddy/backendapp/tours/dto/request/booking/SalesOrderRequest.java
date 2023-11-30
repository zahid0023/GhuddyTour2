package com.ghuddy.backendapp.tours.dto.request.booking;

import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import com.ghuddy.backendapp.tours.dto.request.sales.TourPackageOptionAddToCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderRequest extends BaseRequest {

    @NotNull
    Long orderId;

    private String customerName;

    private String phoneNumber; //new

    private String address;

    @Email
    private String email;

    @NotNull
    private boolean forSelf;

    private String phoneCode;

    @NotNull
    private String currency;

    @NotNull
    List<TourPackageOptionAddToCart> tourShoppingCart;

    @Override
    public void validate() throws AbstractException {

    }
}
