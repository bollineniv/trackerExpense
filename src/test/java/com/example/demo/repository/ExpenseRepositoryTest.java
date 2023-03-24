package com.example.demo.repository;

import com.example.demo.model.Expense;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@DataJpaTest
public class ExpenseRepositoryTest {

    @Autowired
    ExpenseRepository expenseRepository;

    private Expense testExpense1;
    private Expense testExpense2;

    @BeforeEach
    void init(){
        testExpense1 = new Expense();
        testExpense1.setExpense("Note Book");
        testExpense1.setDescription("Classmate NoteBook");
        testExpense1.setAmount(BigDecimal.valueOf(11));
        testExpense1.setCategory("Other");

        testExpense2 = new Expense();
        testExpense2.setExpense("IPhone 14");
        testExpense2.setDescription("Apple Iphone 14");
        testExpense2.setAmount(BigDecimal.valueOf(999));
        testExpense2.setCategory("Electronics");
    }

    @Test
    public void saveExpense(){

        Expense response = expenseRepository.save(testExpense1);

        System.out.println("response: "+response.getId());
        Assert.assertNotNull(response);
        assertThat(response.getId(),notNullValue());
    }
}
