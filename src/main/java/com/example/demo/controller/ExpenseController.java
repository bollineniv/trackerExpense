package com.example.demo.controller;


import com.example.demo.model.Expense;
import com.example.demo.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController

//@RequestMapping("/api/v1")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @GetMapping("/getAllExpense")
    public ResponseEntity<List<Expense>> getAllExpense(){
        List<Expense> expenses =  expenseService.findAll();
//        System.out.println("exp: "+expenses);
        return new ResponseEntity<List<Expense>>(expenses, HttpStatus.OK);
    }

    @GetMapping("/getExpense/{id}")
    public ResponseEntity<Expense> getExpense(@PathVariable long id){
        Expense expense =  expenseService.getExpense(id);
//        if(expense.get().getExpense().isEmpty()){
//
//        }
//        else{
//            return new ResponseEntity<Expense>(expense,HttpStatus.OK);
//        }
//        return expense.isEmpty() ?
//                new ResponseEntity(HttpStatus.BAD_REQUEST):
//                new ResponseEntity(expense,HttpStatus.OK);
//        (expense.get().getExpense().(return new ResponseEntity<Expense>(HttpStatus.OK)));
        return new ResponseEntity<Expense>(expense, HttpStatus.OK);
    }

    @PostMapping("/addExpense")
    public ResponseEntity<Expense> addExpense(@Valid @RequestBody Expense expense){
        Expense expenseResponse = expenseService.addExpense(expense);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(expenseResponse.getId()).toUri();

        return new ResponseEntity<Expense>(expenseResponse,HttpStatus.CREATED)
//                    .created(location).build()
                    ;
    }

    @DeleteMapping("/deleteExpense/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable long id){
        expenseService.deleteExpense(id);
//        System.out.println("exp: "+expenses);
        return new ResponseEntity<String>("Expense is deleted Successfully", HttpStatus.NO_CONTENT);
    }

//    @GetMapping(value="/getExpense/v2")
//    public ResponseEntity<Expense> getExpenseV2(@RequestParam long id){
//        Optional<Expense> expense = expenseService.getExpense(id);
//        return expense.isEmpty() ?
//                new ResponseEntity(HttpStatus.BAD_REQUEST):
//                new ResponseEntity(expense,HttpStatus.OK);
//    }

    @PutMapping("/expense/{id}")
    public ResponseEntity<Expense> updateMapping(@PathVariable long id, @RequestBody Expense expense){
        Expense expenseResponse = expenseService.updateExpense(id,expense);

        return new ResponseEntity<Expense>(expenseResponse,HttpStatus.OK);
    }

    @GetMapping("/getAllExpenseV2")
    public ResponseEntity<List<Expense>> getAllExpenseV2(Pageable page){
        List<Expense> expenses =  expenseService.findAllExpenses(page).toList();
//        System.out.println("exp: "+expenses);
        return new ResponseEntity<List<Expense>>(expenses, HttpStatus.OK);
    }

    @GetMapping("/expense/category")
    public ResponseEntity<List<Expense>> getExpenseByCategory(@RequestParam String category, Pageable page){
        List<Expense> expenses = expenseService.getExpensesByCategory(category, page).toList();

        return new ResponseEntity<List<Expense>>(expenses, HttpStatus.OK);
    }

    @GetMapping("/expense/expensename")
    public ResponseEntity<List<Expense>> getExpenseByName(@RequestParam String expense, Pageable page){
        List<Expense> expenses = expenseService.getExpenseByExpenseName(expense,page).toList();

        return new ResponseEntity<List<Expense>>(expenses,HttpStatus.OK);
    }

    @GetMapping("/expense/byDate")
    public ResponseEntity<List<Expense>> getExpenseBetweenDates(@RequestParam(required=false) Date startDate,
                                                                @RequestParam(required = false) Date endDate,
                                                                Pageable page){
        List<Expense> expenses = expenseService.getExpenseBetweenDates(startDate, endDate,page).toList();

        return new ResponseEntity<List<Expense>>(expenses,HttpStatus.OK);
    }
}
