package com.zefferx.sales.dto;

public record NewPurchaseRequest(
        Integer clientId,
        Integer productId,
        Integer quantity,
        Boolean isFlexibleClient
) {
}
