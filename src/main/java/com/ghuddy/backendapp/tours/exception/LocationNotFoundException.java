package com.ghuddy.backendapp.tours.exception;

import com.ghuddy.backendapp.tours.enums.ErrorCode;
import lombok.Data;

@Data
public class LocationNotFoundException extends Exception {
    private final ErrorCode errorCode;

    public LocationNotFoundException(ErrorCode errorCode) {
        super(errorCode.toString()); // Pass the error message from the ErrorCode enum to the parent constructor
        this.errorCode = errorCode;
    }
}
