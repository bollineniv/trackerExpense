package com.example.demo.controller;

import com.example.demo.model.AuthenticateModel;
import com.example.demo.model.UserModel;
import com.example.demo.model.Users;
import com.example.demo.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class AuthContoller {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public ResponseEntity<Authentication> loginUser(@RequestBody AuthenticateModel login){
//        String username = usersService.getUsername(login.getEmail());
        System.out.println("user11: "+login.getEmail());
        Authentication authentication = authenticationManager.authenticate(
                                                new UsernamePasswordAuthenticationToken(
                                                        login.getEmail(),login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("auth: "+authentication.getPrincipal());
        return new ResponseEntity<Authentication>( authentication,HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Users> createUser(@Valid @RequestBody UserModel user){
        Users userResponse = usersService.createUser(user);
        return new ResponseEntity<Users>(userResponse, HttpStatus.CREATED);
    }
}
