package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Expense;

import com.example.demo.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService{
 
    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    UsersService usersService;
    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Page<Expense> findAllExpenses(Pageable page) {
        return expenseRepository.findAll(page);
    }

    @Override
    public Expense addExpense(Expense expense) {
//        categoryenum test = categoryenum.valueOf(String.valueOf(expense.getCategory()));
//        System.out.println("enum: "+test);
////        categoryenum test = categoryenum.valueOf(String.valueOf(expense.getCategory()));
//        System.out.println("val: "+expense.getCategory());

        System.out.println("val: "+usersService.getLoggedInUser());
        expense.setUser(usersService.getLoggedInUser());
        Expense expenseResponse = expenseRepository.save(expense);

        return expenseResponse;
    }

    @Override
    public Expense getExpense(long id) {
        Optional<Expense> expenseResponse = expenseRepository.findById(id);
        if(expenseResponse.isPresent()) {
            return expenseResponse.get();
        }
        throw new ResourceNotFoundException("Expense is not found for Id: "+id);
    }

    @Override
    public void deleteExpense(long id) {
        Expense expense = getExpense(id);
        expenseRepository.delete(expense);
//        return expenseResponse;
    }

    @Override
    public Expense updateExpense(long id, Expense expense) {
        Expense existingExpense = getExpense(id);

        existingExpense.setExpense(expense.getExpense()!=null ? expense.getExpense() : existingExpense.getExpense());
        existingExpense.setDescription(expense.getDescription()!=null ? expense.getDescription() : existingExpense.getDescription());
        existingExpense.setAmount(expense.getAmount()!=null ? expense.getAmount() : existingExpense.getAmount());
        existingExpense.setCategory(expense.getCategory()!=null ? expense.getCategory() : existingExpense.getCategory());
        existingExpense.setDate(expense.getDate()!=null ? expense.getDate() : existingExpense.getDate());
        return expenseRepository.save(existingExpense);
    }

    @Override
    public Page<Expense> getExpensesByCategory(String category, Pageable page) {
       return expenseRepository.findByCategory(category,page);
    }

    @Override
    public Page<Expense> getExpenseByExpenseName(String expense, Pageable page) {
        return expenseRepository.findByExpenseContaining(expense,page);
    }

    public Page<Expense> getExpenseBetweenDates(Date startDate, Date endDate, Pageable page){
        if(startDate == null){
            startDate = new Date(0);
        }
        if(endDate == null){
            endDate = new Date(System.currentTimeMillis());
        }
        return expenseRepository.findByDateBetween(startDate,endDate,page);
    }


}
