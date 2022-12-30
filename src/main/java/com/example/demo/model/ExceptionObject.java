package com.example.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionObject {

    private Integer statusCode;

    private String errorDescription;

    private Date timestamp;
}
