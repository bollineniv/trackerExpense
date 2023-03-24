package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomUserDetailsService implements UserDetailsService {
//public class CustomUserDetailsService implements AuthenticationProvider {

//    public CustomUserDetailsService() {
//    }

    @Autowired
    private UsersRepository usersRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        System.out.println("yyyy");
        Users existingUser =  usersRepository.findByEmail(email).orElseThrow(()->
                                    new UsernameNotFoundException("User not found: "+email));
        return new org.springframework.security.core.userdetails.User(existingUser.getEmail(), existingUser.getPassword(), new ArrayList<>());
    }

//    @Override
//    public Authentication authenticate_old(Authentication authentication) throws AuthenticationException {
////        System.out.println("test:");
//        Pattern pattern = Pattern.compile("^[A-Za-z0-9._]{1,16}+@{1}+[a-z]{1,7}\\.[a-z]{1,3}$");
//        Matcher mail;
//        Users user;
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
////        System.out.println("test 12:"+username);
////        System.out.println("pas 12:"+password);
//        mail = pattern.matcher(username);
//        if(mail.find()){
//            user = usersRepository.findByEmail(username)
//                .orElseThrow(()-> new RuntimeException("email not found: "+username));
//
//        }
//        else{
//            user = usersRepository.findByUsername(username)
//                    .orElseThrow(()-> new RuntimeException("username not found: "+username));
//        }
//
////        System.out.println("pas122 12:"+user.getPassword());
////        System.out.println("pass122: "+authPassword);
//        if(password.equals(user.getPassword())){
////        if(passwordEncoder.matches(password,user.getPassword())){
//            return new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>());
////            return new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>());
//        }
//        else{
//            throw new RuntimeException("Password mismatch");
//        }
//    }
//*******************************
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        Users user;
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        user = usersRepository.findByEmail(username)
//                .orElseThrow(()-> new RuntimeException("email not found: "+username));
//
//        if(password.equals(user.getPassword())){
////        if(passwordEncoder.matches(password,user.getPassword())){
//            return new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>());
//        }
//        else{
//            throw new RuntimeException("Password mismatch");
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }


}
