package com.example.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserModel {

    @NotBlank(message = "Name should not be blank")
    private String name;

    @NotNull(message = "username should not be blank")
    private String username;

    @NotNull(message = "Email should not be null")
    @Email(message = "Enter valid email")
    private String email;

    @NotBlank(message = "password should not be blank")
    @Size(min=5, message="Password should be at least 5 characters")
    private String password;

    private Long age=0L;
}
