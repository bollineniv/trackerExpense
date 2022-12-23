package com.example.demo.service;

import com.example.demo.model.Expense;

import java.util.List;


public interface ExpenseService {

    List<Expense> findAll();
}
