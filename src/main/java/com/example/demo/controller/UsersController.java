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

    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Long id){
        Users userResponse = usersService.getUser(id);

        return new ResponseEntity<Users>(userResponse,HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateUser(@RequestBody UserModel user, @PathVariable Long id){
        Users userResponse = usersService.updateUser(user,id);
        return new ResponseEntity<Users>(userResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id){
        usersService.deleteUser(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
