package com.zefferx.sales.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @NoArgsConstructor @AllArgsConstructor @Data public class Billing {

    public static Integer staticId = 1;
    @Id private Integer id;
    private Integer total;

}

