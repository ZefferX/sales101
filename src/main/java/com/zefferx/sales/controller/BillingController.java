package com.zefferx.sales.controller;

import com.zefferx.sales.dto.DevolutionResponse;
import com.zefferx.sales.dto.NewPurchaseRequest;
import com.zefferx.sales.dto.ReturnPurchaseRequest;
import com.zefferx.sales.dto.SaleResponse;
import com.zefferx.sales.service.BillingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/return")
    public DevolutionResponse processReturn(@RequestBody ReturnPurchaseRequest request){
        return billingService.originalTicket(request);

    }

}


