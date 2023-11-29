package com.ghuddy.backendapp.tours.es.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.dto.data.BillData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ESBillCalculationResponse extends BaseResponse {
    @JsonProperty("bill_descriptions")
    BillData billData;

    public ESBillCalculationResponse(BillData billData, String requestId) {
        this.billData = billData;
        setRequestId(requestId);
    }
}
