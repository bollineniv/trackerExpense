package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Expense Tracker API", version = "1.0", description = "Save Expenses"))

//@EnableSwagger2
//@EntityScan(basePackages = {"com.example.model"})


public class TrackerExpenseApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackerExpenseApplication.class, args);
	}

}
