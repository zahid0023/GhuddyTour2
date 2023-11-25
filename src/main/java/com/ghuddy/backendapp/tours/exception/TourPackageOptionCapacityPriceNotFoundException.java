package com.ghuddy.backendapp.tours.exception;

import com.ghuddy.backendapp.tours.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TourPackageOptionCapacityPriceNotFoundException extends Exception {
    private final ErrorCode errorCode;

    public TourPackageOptionCapacityPriceNotFoundException(ErrorCode errorCode) {
        super(errorCode.toString());
        this.errorCode = errorCode;
    }
}
