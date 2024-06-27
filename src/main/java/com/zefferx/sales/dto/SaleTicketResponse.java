package com.zefferx.sales.dto;

public record SaleTicketResponse(Integer productId,
                                 String productName,
                                 Integer productPrice,
                                 Integer clientId,
                                 Integer clientQuantityRequired,
                                 Integer totalSale) {
}



