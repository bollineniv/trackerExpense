package com.example.demo.controller;

import com.example.demo.model.AuthenticateModel;
import com.example.demo.model.JwtResponse;
import com.example.demo.model.UserModel;
import com.example.demo.model.Users;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.UsersService;
import com.example.demo.util.JwtTokenUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<Authentication> loginUser(@RequestBody AuthenticateModel login) throws Exception {
//        String username = usersService.getUsername(login.getEmail());
//        System.out.println("user11: "+login.getEmail());
        Authentication authentication = authenticationManager.authenticate(
                                                new UsernamePasswordAuthenticationToken(
                                                        login.getEmail(),login.getPassword()));

        authenticate(login.getEmail(),login.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        System.out.println("auth: "+authentication.getPrincipal().toString());
        return new ResponseEntity<Authentication>( authentication,HttpStatus.OK);
    }

    @PostMapping("/loginV2")
    public ResponseEntity<JwtResponse> loginUserV2(@RequestBody AuthenticateModel login) throws Exception {
//        String username = usersService.getUsername(login.getEmail());
//        System.out.println("user11: "+login.getEmail());

        authenticate(login.getEmail(),login.getPassword());
        //Token generation
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(login.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println("token: "+token);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        System.out.println("auth: "+authentication.getPrincipal().toString());
        return new ResponseEntity<JwtResponse>(new JwtResponse(token),HttpStatus.OK);
    }

    private void authenticate(String email, String password) throws Exception {

        try { authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            email,password));
        }
        catch (DisabledException e){
            throw new Exception("User Disabled");
        }
        catch (BadCredentialsException e){
            throw new Exception("Bad Credentials ");
        }

    }

    @PostMapping("/register")
    public ResponseEntity<Users> createUser(@Valid @RequestBody UserModel user){
        Users userResponse = usersService.createUser(user);
        return new ResponseEntity<Users>(userResponse, HttpStatus.CREATED);
    }
}
