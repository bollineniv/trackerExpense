package com.example.demo.repository;

import com.example.demo.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    Page<Expense> findByCategory(String category, Pageable page);

    Page<Expense> findByExpenseContaining(String expense, Pageable page);

    Page<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page);

}
