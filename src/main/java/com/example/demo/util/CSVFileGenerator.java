package com.example.demo.util;

import com.example.demo.model.Expense;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.Writer;
import java.sql.ResultSet;
import java.util.List;

@Component
public class CSVFileGenerator {

    public void writeToCSV(List<Expense> expenses, Writer writer){
        try{
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            System.out.println("size: "+expenses.size());

            printer.printRecord("Id","Expense","Description","Amount","Category","Date",
                                        "CreatedAt","UpdatedAt");
            for(Expense expense: expenses){
                printer.printRecord(expense.getId(),expense.getExpense(),
                                    expense.getDescription(),expense.getAmount(),
                                        expense.getCategory(),expense.getDate(),
                                        expense.getCreatedAt(),expense.getUpdatedAt());
            }
            System.out.println("print:: "+printer.toString());
        }
        catch (Exception e){

        }

    }
}
