package com.example.demo.service;

import com.example.demo.model.Expense;

import java.util.List;
import java.util.Optional;


public interface ExpenseService {

    List<Expense> findAll();

    Expense addExpense(Expense expense);

    Optional<Expense> getExpense(long id);

    void deleteExpense(long id);
}
