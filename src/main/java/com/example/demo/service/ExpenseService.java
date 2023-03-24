package com.example.demo.service;

import com.example.demo.model.Expense;
import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.util.List;
import java.util.Optional;


public interface ExpenseService {

    List<Expense> findAll();

    Page<Expense> findAllExpenses(Pageable page);

    Expense addExpense(Expense expense) throws MessagingException, IOException;

    Expense getExpense(long id);

    void deleteExpense(long id);

    Expense updateExpense(long id, Expense expense);

    Page<Expense> getExpensesByCategory(String category, Pageable page);

    Page<Expense> getExpenseByExpenseName(String expense, Pageable page);

    Page<Expense> getExpenseBetweenDates(Date startDate, Date endDate, Pageable page);

    void writeExpenseToCSV(Writer writer);

    int deleteAllExpenses();
}
