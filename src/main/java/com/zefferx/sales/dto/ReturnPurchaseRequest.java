package com.zefferx.sales.dto;

public record ReturnPurchaseRequest(
        Integer ticketId,
        Integer quantity,
        Boolean isFlexibleClient
) {
}
