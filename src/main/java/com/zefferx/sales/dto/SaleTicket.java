package com.zefferx.sales.dto;

public record SaleTicket(Integer productId,
                         String productName,
                         Integer productPrice,
                         Integer clientId,
                         Integer clientQuantityRequired,
                         Integer totalSale) {
}



