package com.example.demo.controller;

import com.example.demo.model.UserModel;
import com.example.demo.model.Users;
import com.example.demo.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

//    @PostMapping("/register")
//    public ResponseEntity<Users> createUser(@Valid @RequestBody UserModel user){
//        Users userResponse = usersService.createUser(user);
//        return new ResponseEntity<Users>(userResponse, HttpStatus.CREATED);
//    }

    @GetMapping("/profile")
    public ResponseEntity<Users> getUser(){
        Users userResponse = usersService.getUser();

        return new ResponseEntity<Users>(userResponse,HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<Users> updateUser(@RequestBody UserModel user){
        Users userResponse = usersService.updateUser(user);
        return new ResponseEntity<Users>(userResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<HttpStatus> deleteUser(){
        usersService.deleteUser();
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
