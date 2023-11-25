package com.ghuddy.backendapp.tours.enums;

/*
 * Error Codes:
 * Location Error : starts with 0, example 00001
 * Tour Error: starts with 1, example 10001
 * */
public enum ErrorCode {


    // registration 4 digits starts with 0 for otp
    OTP_NOT_MATCHED("0001"),
    NO_ACTIVE_OTP_PRESENT("0002"),
    MORE_THAN_FIVE_TIME("0003"),
    LESS_THAN_FIVE_MINUTES("0004"),
    OTP_ALREADY_VERIFIED("0005"),
    USER_ALREADY_EXISTS("0006"),

    // Location Exceptions
    // Starts with 0
    LOCATION_NOT_FOUND("00001"),

    // Tour Exceptions
    // Starts with 1
    TOUR_NOT_FOUND("10001"),
    TOUR_ALREADY_EXIST("10002"),

    // Activity Exceptions
    // Starts with 2
    ACTIVITY_TYPE_ALREADY_EXISTS("20001"),
    ACTIVITY_TYPE_NOT_FOUND("20002"),
    ACTIVITY_ALREADY_EXISTS("20003"),
    ACTIVITY_NOT_FOUND("2004"),
    ACTIVITY_CAN_NOT_BE_SAVED("2005"),


    // Tour Package Option Exceptions
    TOUR_PACKAGE_OPTION_DOES_NOT_EXIST("30001"),
    TOUR_PACKAGE_OPTION_CAPACITY_DOES_NOT_EXIST("30002"),

    // User Exceptions
    MERCHANT_NOT_FOUND("50001"),

    // Tour Package Exceptions
    TOUR_PACKAGE_NOT_FOUND("40001"),


    // Generic Exceptions
    // Starts with 11
    LIST_IS_EMPTY("11001"),
    DATA_SAVE_ERROR("11002"),
    ONE_OR_MORE_ENTITIES_NOT_FOUND("11003"),
    ENTITY_NOT_FOUND("11005");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}

