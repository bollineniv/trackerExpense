package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.UserExistException;
import com.example.demo.model.UserModel;
import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public Users createUser(UserModel user) {
//        System.out.println("test: "+user.getEmail());
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new UserExistException("Email already exist: "+user.getEmail());
        }

        Users newUser = new Users();
        BeanUtils.copyProperties(user,newUser);
        newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public Users getUser() {
        Long userId = getLoggedInUser().getId();
      return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not exists:"+userId));
    }

    @Override
    public Users updateUser(UserModel user) {
        Users existingUser = getUser();
        existingUser.setName(user.getName() !=null ? user.getName() : existingUser.getName());
        existingUser.setUsername(user.getUsername() !=null ? user.getUsername() : existingUser.getUsername());
        existingUser.setEmail(user.getEmail()!=null ? user.getEmail() : existingUser.getEmail());
        existingUser.setPassword(user.getPassword()!=null ? (user.getPassword()) : existingUser.getPassword());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser() {
        Users user = getUser();
        userRepository.delete(user);
    }

    @Override
    public String getUsername(String email) {
        Users user =  userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("User not exists:"+email));

        return user.getUsername();
    }

    @Override
    public Users getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email =authentication.getName();

        return userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found "+email));
    }
}
