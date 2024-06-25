package com.zefferx.sales.dto;

public record NewProductRequest(
        String name,
        Integer price,
        Integer quantity
) {
}
