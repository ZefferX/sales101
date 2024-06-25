package com.zefferx.sales.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_id_sequence",
            sequenceName = "product_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_id_sequence"
    )

    private Integer id;
    private String name;
    private Integer price;
    private Integer quantity;

}
