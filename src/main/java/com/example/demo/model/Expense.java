package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "tbl_expense")
@Getter
@Setter
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1)
    private String expense;

    private String description;

    private BigDecimal amount;
}

