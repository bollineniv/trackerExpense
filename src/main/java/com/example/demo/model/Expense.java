package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_expense")
@Getter
@Setter
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, message = "Expense name should be at least 2 characters")
//    @NotNull(message = "Expense Name should be Mandatory")
    @NotBlank(message = "Expense name should not be null")
    private String expense;

    private String description;

    private BigDecimal amount;

    @NotBlank(message = "Category should not be null")
    private String category;

//    @Enumerated(EnumType.STRING)
////    @NotBlank(message = "Category should not be null")
//    private CategoryEnum category;

    private Date date;

    @Column(name="created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;


}

