package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Expense;

import com.example.demo.model.FilesModel;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.util.CSVFileGenerator;
import com.example.demo.util.EmailUtils;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService{
 
    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    UsersService usersService;

    @Autowired
    FilesService filesService;
    @Autowired
    CSVFileGenerator csvFileGenerator;

    @Autowired
    EmailUtils emailUtils;
    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Page<Expense> findAllExpenses(Pageable page) {

        return expenseRepository.findByUserId(usersService.getLoggedInUser().getId(), page);
    }

    @Override
    public Expense addExpense(Expense expense) throws MessagingException, IOException {
//        categoryenum test = categoryenum.valueOf(String.valueOf(expense.getCategory()));
//        System.out.println("enum: "+test);
////        categoryenum test = categoryenum.valueOf(String.valueOf(expense.getCategory()));
//        System.out.println("val: "+expense.getCategory());
        System.out.println("test:: "+expense);

        System.out.println("val: "+usersService.getLoggedInUser());
        expense.setUser(usersService.getLoggedInUser());

//        System.out.println("file: "+expense.getFile());
//        expense.setFile(filesService.getClass());

        Expense expenseResponse = expenseRepository.save(expense);

        FilesModel requestedReciept = filesService.getReceipt("eq1.png");

        emailUtils.sendEmailWithAttachment( usersService.getLoggedInUser().getEmail(),
                "AutoReply of Saved Expense",
                expense.toString(),
                requestedReciept.getName(),
                requestedReciept.getPicByte()
                );
        return expenseResponse;
    }

    @Override
    public Expense getExpense(long id) {
        Optional<Expense> expenseResponse = expenseRepository
                    .findByUserIdAndId(usersService.getLoggedInUser().getId(), id);
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
//       return expenseRepository.findByCategory(category,page);
        return expenseRepository.findByUserIdAndCategory(usersService.getLoggedInUser().getId(),
                    category, page);
    }

    @Override
    public Page<Expense> getExpenseByExpenseName(String expense, Pageable page) {
//        return expenseRepository.findByExpenseContaining(expense,page);
        return expenseRepository.findByUserIdAndExpenseContaining(usersService.getLoggedInUser().getId(),
                            expense,page);
    }

    public Page<Expense> getExpenseBetweenDates(Date startDate, Date endDate, Pageable page){
        if(startDate == null){
            startDate = new Date(0);
        }
        if(endDate == null){
            endDate = new Date(System.currentTimeMillis());
        }
//        return expenseRepository.findByDateBetween(startDate,endDate,page);
        return expenseRepository.findByUserIdAndDateBetween(usersService.getLoggedInUser().getId(),
                        startDate, endDate, page);
    }

    @Override
    public void writeExpenseToCSV(Writer writer) {
        List<Expense> expenses = expenseRepository.findAllByUserId(usersService.getLoggedInUser().getId());
        System.out.println("test: "+expenses);

        csvFileGenerator.writeToCSV(expenses, writer);
    }

    @Override
    public int deleteAllExpenses() {
        List<Expense> allExpense = findAll();
        expenseRepository.deleteAll();
        return allExpense.size();
    }


}
