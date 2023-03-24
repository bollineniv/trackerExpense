package com.example.demo.repository;

import com.example.demo.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.sql.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    Page<Expense> findByCategory(String category, Pageable page);

    Page<Expense> findByExpenseContaining(String expense, Pageable page);

    Page<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page);

    Page<Expense> findByUserId(Long userId, Pageable page);

    Optional<Expense> findByUserIdAndId(Long userId, Long expenseId);

    Page<Expense> findByUserIdAndCategory(Long userId, String category, Pageable page);

    Page<Expense> findByUserIdAndExpenseContaining(Long userId, String expense, Pageable page);

    Page<Expense> findByUserIdAndDateBetween(Long userId, Date startDate, Date endDate, Pageable page);

    List<Expense> findAllByUserId(Long id);
}
