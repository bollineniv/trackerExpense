package com.example.demo.service;

import com.example.demo.model.Expense;
import com.example.demo.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    ExpenseRepository expenseRepository;
    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense addExpense(Expense expense) {
        Expense expenseResponse = expenseRepository.save(expense);
        return expenseResponse;
    }

    @Override
    public Optional<Expense> getExpense(long id) {
        Optional<Expense> expenseResponse = expenseRepository.findById(id);
        return expenseResponse;
    }

    @Override
    public void deleteExpense(long id) {
        expenseRepository.deleteById(id);
//        return expenseResponse;
    }
}
