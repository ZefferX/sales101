package com.zefferx.sales.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {

    @Id
    @SequenceGenerator(
            name = "client_id_sequence",
            sequenceName = "client_id_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_id_sequence")

    private Integer id;
    private Integer money;
    private Integer age;
}
