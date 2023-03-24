package com.example.demo.exception;

public class FileUploadException extends RuntimeException{
    public static final long serialVersionUID=1L;


    public FileUploadException(String message) {
        super(message);
    }
}
