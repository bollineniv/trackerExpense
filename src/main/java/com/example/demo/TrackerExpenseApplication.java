package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EntityScan(basePackages = {"com.example.model"})


public class TrackerExpenseApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackerExpenseApplication.class, args);
	}

}
