package com.zefferx.sales.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @NoArgsConstructor @AllArgsConstructor @Data public class Billing {

    @Id
    @SequenceGenerator(
            name = "billing_id_sequence",
            sequenceName = "billing_id_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "billing_id_sequence")

    private Integer id;
    private Integer total;

}

