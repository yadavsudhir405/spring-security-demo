package com.example.demo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("Flight")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
@Builder(toBuilder = true)
public class Flight {

    @Id
    private Integer id;

    private String name;

    private String pilot;

    private String status;
}
