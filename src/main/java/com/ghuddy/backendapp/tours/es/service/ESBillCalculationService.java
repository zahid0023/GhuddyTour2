package com.ghuddy.backendapp.tours.es.service;

import com.ghuddy.backendapp.tours.dto.response.sales.BillCalculationRequest;

import java.io.IOException;

public interface ESBillCalculationService {
    void calculateBill(BillCalculationRequest billCalculationRequest) throws IOException;
}
