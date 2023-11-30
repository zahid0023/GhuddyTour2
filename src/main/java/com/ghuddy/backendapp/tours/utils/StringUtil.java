package com.ghuddy.backendapp.tours.utils;

import com.ghuddy.backendapp.tours.dto.request.sales.TourPackageOptionAddToCart;

public class StringUtil {
    public static String slugify(String str1, String str2) {
        // Remove special characters and spaces, and convert to lowercase
        String formattedStr1 = str1.replaceAll("[^a-zA-Z0-9]+", "-").toLowerCase();
        String formattedStr2 = str2.replaceAll("[^a-zA-Z0-9]+", "-").toLowerCase();

        // Concatenate the formatted strings with a hyphen
        String tag = formattedStr1 + "-" + formattedStr2;

        return tag;
    }

    public static String tourPackageName(String tourName, String packageTypeName) {
        return tourName + " - " + packageTypeName;
    }

    public static String tourName(String destinationLocationName, Integer numberOfDays, Integer numberOfNights) {
        String day = numberOfDays > 1 ? numberOfDays + " Days" : numberOfDays == 1 ? numberOfDays + " Day" : "";
        String night = numberOfNights > 1 ? numberOfNights + " Nights" : numberOfNights == 1 ? numberOfNights + " Night" : "";
        return destinationLocationName + " Tour - " + day + " " + night;
    }

    public static String generateKey(Long availableTourPackageId,
                               Long accommodationOptionId,
                               Long foodOptionId,
                               Long transferOptionId,
                               Long transportationOptionId,
                               Long guideOptionId,
                               Long spotEntryOptionId) {
        return availableTourPackageId + "_" + accommodationOptionId + "_" + foodOptionId + "_" + transferOptionId + "_" + transportationOptionId + "_" + guideOptionId + "_" + spotEntryOptionId;
    }

}

