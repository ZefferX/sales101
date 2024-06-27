package com.zefferx.sales.dto;

public record ReturnPurchaseRequest(
        Integer clientId,
        Integer productId,
        Integer quantity
) {
}
