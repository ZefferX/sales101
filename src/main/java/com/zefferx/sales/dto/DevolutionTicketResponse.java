package com.zefferx.sales.dto;

public record DevolutionTicketResponse(Integer productId,
                                       String productName,
                                       Integer productPrice,
                                       Integer clientId,
                                       Integer returnedQuantity,
                                       Integer returnedMoney) {
}
