package com.ghuddy.backendapp.tours.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OptionPriceData {
    @Schema(description = "The sum of all the options")
    @JsonProperty("ghuddy_platform_calculated_option_price")
    private BigDecimal ghuddyPlatformCalculatedOptionPrice;
    @Schema(description = "The subsidy merchant want to give, could me positive or negative")
    @JsonProperty("merchant_subsidy_amount")
    private BigDecimal merchantSubsidyAmount;
    @Schema(description = "The net option price after taking merchant subsidy into consideration")
    @JsonProperty("net_option_price_after_merchant_subsidy")
    private BigDecimal netOptionPriceAfterMerchantSubsidy;
    @Schema(description = "The ghuddy platform commission")
    @JsonProperty("ghuddy_platform_commission_amount")
    private BigDecimal ghuddyPlatformCommissionAmount;
    @Schema(description = "The sum of netOptionPriceAfterMerchantSubsidyAmount + ghuddyPlatformCommission")
    @JsonProperty("net_option_price_after_ghuddy_commission")
    private BigDecimal netOptionPriceAfterGhuddyCommission;
    @Schema(description = "The ghuddy web site black price which is taking consideration of payment gateway percentage")
    @JsonProperty("ghuddy_website_black_price")
    private BigDecimal ghuddyWebsiteBlackPrice;
    @Schema(description = "The subsidy ghuddy want to provide to the travellers")
    @JsonProperty("ghuddy_subsidy_amount")
    private BigDecimal ghuddySubsidyAmount;
    @Schema(description = "The net option price after taking into consideration of ghuddy subsidy amount")
    @JsonProperty("net_option_price_after_ghuddy_subsidy")
    private BigDecimal netOptionPriceAfterGhuddySubsidy;
    @Schema(description = "The ghuddy web site red price which will be the price the travellers will pay to go on a tour")
    @JsonProperty("ghuddy_website_red_price")
    private BigDecimal ghuddyWebsiteRedPrice;
    @Schema(description = "The payment gateway service amount")
    @JsonProperty("payment_gateway_amount")
    private BigDecimal paymentGateWayAmount;
}
