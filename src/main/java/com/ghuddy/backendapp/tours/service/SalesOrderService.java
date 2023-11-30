package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.exception.AbstractException;
import com.ghuddy.backendapp.tours.dto.request.booking.SalesOrderRequest;
import com.ghuddy.backendapp.tours.model.entities.booking.SalesOrderEntity;

import java.io.IOException;

public interface SalesOrderService {
    void updateSalesOrder(SalesOrderRequest request) throws AbstractException, IOException, InterruptedException;

    SalesOrderEntity fetchSalesOrderById(Long orderId, String requestId) throws AbstractException;

    SalesOrderEntity fetchSalesOrderByOrderCode(String orderCode, String requestId) throws AbstractException;
}
