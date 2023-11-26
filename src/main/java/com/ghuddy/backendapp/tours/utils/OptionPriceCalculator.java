package com.ghuddy.backendapp.tours.utils;

import com.ghuddy.backendapp.tours.model.data.OptionPriceData;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@UtilityClass
@Slf4j
public class OptionPriceCalculator {

    // Constants for commission percentages
    private static final BigDecimal GHUDDY_COMMISSION_PERCENTAGE = BigDecimal.valueOf(15); // Replace with your actual percentage
    private static final BigDecimal PAYMENT_GATEWAY_COMMISSION_DIVIDER = BigDecimal.valueOf(0.975); // Replace with your actual multiplier

    public static OptionPriceData getBlackPrice(BigDecimal ghuddyPlatformCalculatedOptionPrice, BigDecimal merchantSubsidiaryAmount) {

        OptionPriceData optionPriceData = new OptionPriceData();
        optionPriceData.setGhuddyPlatformCalculatedOptionPrice(ghuddyPlatformCalculatedOptionPrice);
        optionPriceData.setMerchantSubsidyAmount(merchantSubsidiaryAmount);

        // Step 1: Calculate the option price after merchant subsidy
        BigDecimal netOptionPriceAfterMerchantSubsidy = calculateNetOptionPriceAfterSubsidy(ghuddyPlatformCalculatedOptionPrice, merchantSubsidiaryAmount);
        optionPriceData.setNetOptionPriceAfterMerchantSubsidy(netOptionPriceAfterMerchantSubsidy);
        log.info("Net Option Price After Merchant Subsidy: " + netOptionPriceAfterMerchantSubsidy);

        // Step 2: Calculate Ghuddy Commission Amount
        BigDecimal ghuddyCommissionAmount = calculateGhuddyCommissionAmount(netOptionPriceAfterMerchantSubsidy);
        optionPriceData.setGhuddyPlatformCommissionAmount(ghuddyCommissionAmount);
        log.info("Ghuddy Commission Amount: " + ghuddyCommissionAmount);

        // Step 3: Calculate Option Price After Ghuddy Commission
        BigDecimal optionPriceAfterGhuddyCommission = calculateOptionPriceAfterGhuddyCommission(netOptionPriceAfterMerchantSubsidy, ghuddyCommissionAmount);
        optionPriceData.setNetOptionPriceAfterGhuddyCommission(optionPriceAfterGhuddyCommission);
        log.info("Option Price After Ghuddy Commission: " + optionPriceAfterGhuddyCommission);

        // Step 4: Calculate Black Price (Price user has to pay to cover payment gateway commission)
        BigDecimal blackPrice = calculateBlackPrice(optionPriceAfterGhuddyCommission);
        optionPriceData.setGhuddyWebsiteBlackPrice(blackPrice);
        log.info("Black Price: " + blackPrice);
        return optionPriceData;
    }

    public static OptionPriceData getRedPrice(BigDecimal optionPriceAfterGhuddyCommission, BigDecimal ghuddySubsidyAmount, OptionPriceData optionPriceData) {
        optionPriceData.setGhuddySubsidyAmount(ghuddySubsidyAmount);

        // Step 1: Calculate the option price after ghuddy subsidy
        BigDecimal netOptionPriceAfterGhuddySubsidy = calculateNetOptionPriceAfterSubsidy(optionPriceAfterGhuddyCommission, ghuddySubsidyAmount);
        optionPriceData.setNetOptionPriceAfterGhuddySubsidy(netOptionPriceAfterGhuddySubsidy);
        log.info("Net Option Price After Ghuddy Subsidy: " + netOptionPriceAfterGhuddySubsidy);

        // Step 2: Calculate red Price (Price user has to pay to cover payment gateway commission)
        BigDecimal redPrice = calculateRedPrice(netOptionPriceAfterGhuddySubsidy);
        optionPriceData.setGhuddyWebsiteRedPrice(redPrice);
        optionPriceData.setPaymentGateWayAmount(redPrice.subtract(netOptionPriceAfterGhuddySubsidy));
        log.info("Red Price: " + redPrice);
        return optionPriceData;
    }



    private static BigDecimal calculateNetOptionPriceAfterSubsidy(BigDecimal basePrice, BigDecimal subsidyAmount) {
        return basePrice.add(subsidyAmount);
    }

    private static BigDecimal calculateGhuddyCommissionAmount(BigDecimal netOptionPrice) {

        // Calculate commission as a percentage of netOptionPrice
        BigDecimal commission = GHUDDY_COMMISSION_PERCENTAGE.divide(BigDecimal.valueOf(100)).multiply(netOptionPrice);

        // Ensure that the result has the desired scale and rounding
        return commission.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private static BigDecimal calculateOptionPriceAfterGhuddyCommission(BigDecimal netOptionPrice, BigDecimal ghuddyCommissionAmount) {
        return netOptionPrice.add(ghuddyCommissionAmount);
    }

    private static BigDecimal calculateBlackPrice(BigDecimal optionPriceAfterGhuddyCommission) {
        return optionPriceAfterGhuddyCommission.divide(PAYMENT_GATEWAY_COMMISSION_DIVIDER, 2, BigDecimal.ROUND_HALF_UP);
    }

    private static BigDecimal calculateRedPrice(BigDecimal optionPriceAfterGhuddySubsidy) {
        return optionPriceAfterGhuddySubsidy.divide(PAYMENT_GATEWAY_COMMISSION_DIVIDER, 2, BigDecimal.ROUND_HALF_UP);
    }
}
