package com.example.demo.controller;


import com.example.demo.model.Expense;
import com.example.demo.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController

@RequestMapping("/api/v1")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @GetMapping("/getAllExpense")
    public ResponseEntity<List<Expense>> getAllExpense(){
        List<Expense> expenses =  expenseService.findAll();
        System.out.println("exp: "+expenses);
        return new ResponseEntity<List<Expense>>(expenses, HttpStatus.OK);
    }

}
