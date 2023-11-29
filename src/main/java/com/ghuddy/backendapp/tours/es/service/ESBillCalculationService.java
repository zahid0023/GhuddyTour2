package com.ghuddy.backendapp.tours.es.service;

import com.ghuddy.backendapp.tours.dto.response.sales.BillCalculationRequest;
import com.ghuddy.backendapp.tours.es.dto.response.ESBillCalculationResponse;

import java.io.IOException;

public interface ESBillCalculationService {
    ESBillCalculationResponse calculateBill(BillCalculationRequest billCalculationRequest) throws IOException;
}
