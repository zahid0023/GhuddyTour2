package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.booking.SalesOrderRequest;
import com.ghuddy.backendapp.tours.service.SalesOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user/sales-order")
public class SalesOrderControllerForUser {
    private final SalesOrderService salesOrderService;

    public SalesOrderControllerForUser(SalesOrderService salesOrderService) {
        this.salesOrderService = salesOrderService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createSalesOrder(@RequestBody SalesOrderRequest request) throws Exception {
        salesOrderService.updateSalesOrder(request);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
