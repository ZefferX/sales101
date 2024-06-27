package com.zefferx.sales.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaleTicket {
    @Id
    @SequenceGenerator(
            name = "sale_ticket_id_sequence",
            sequenceName = "sale_ticket_id_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sale_ticket_id_sequence")

    private Integer id;
    private Integer productId;
    private String productName;
    private Integer productPrice;
    private Integer clientId;
    private Integer clientQuantityRequired;
    private Integer totalSale;

    private Integer returnedQuantity;
    private Integer returnedMoney;

    public SaleTicket(Integer productId, String productName, Integer productPrice, Integer clientId, Integer clientQuantityRequired, Integer totalSale) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.clientId = clientId;
        this.clientQuantityRequired = clientQuantityRequired;
        this.totalSale = totalSale;
    }

    public SaleTicket(Integer productId,
                      Integer productPrice,
                      String productName,
                      Integer clientId,
                      Integer returnedQuantity,
                      Integer returnedMoney){
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.clientId = clientId;
        this.returnedQuantity = returnedQuantity;
        this.returnedMoney = returnedMoney;

    }
}
