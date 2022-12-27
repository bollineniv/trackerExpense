package com.example.demo.controller;


import com.example.demo.model.Expense;
import com.example.demo.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
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

    @GetMapping("/getExpense/{id}")
    public ResponseEntity<Expense> getExpense(@PathVariable long id){
        Optional<Expense> expense =  expenseService.getExpense(id);
//        if(expense.get().getExpense().isEmpty()){
//
//        }
//        else{
//            return new ResponseEntity<Expense>(expense,HttpStatus.OK);
//        }
        return expense.isEmpty() ?
                new ResponseEntity(HttpStatus.BAD_REQUEST):
                new ResponseEntity(expense,HttpStatus.OK);
//        (expense.get().getExpense().(return new ResponseEntity<Expense>(HttpStatus.OK)));
//        return new ResponseEntity<Expense>(HttpStatus.OK);
    }

    @PostMapping("/addExpense")
    public ResponseEntity<Expense> addExpense(@Valid @RequestBody Expense expense){
        Expense expenseResponse = expenseService.addExpense(expense);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(expenseResponse.getId()).toUri();

        return new ResponseEntity<Expense>(expenseResponse,HttpStatus.OK)
//                    .created(location).build()
                    ;
    }

    @DeleteMapping("/deleteExpense/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable long id){
        expenseService.deleteExpense(id);
//        System.out.println("exp: "+expenses);
        return new ResponseEntity<String>("Expense is deleted Successfully", HttpStatus.OK);
    }

}
