package com.zefferx.sales.controller;

import com.zefferx.sales.dto.NewBillingRequest;
import com.zefferx.sales.dto.NewPurchaseRequest;
import com.zefferx.sales.dto.SaleResponse;
import com.zefferx.sales.dto.SaleTicket;
import com.zefferx.sales.model.Billing;
import com.zefferx.sales.service.BillingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cashregister")
@AllArgsConstructor
public class BillingController {
    private final BillingService billingService;

    @PostMapping
    public SaleResponse executePurchase (@RequestBody NewPurchaseRequest request) {
        SaleResponse saleResponse = billingService.completedSale(request);
        return saleResponse;
    }




}


