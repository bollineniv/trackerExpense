package com.example.demo.service;

import com.example.demo.model.UserModel;
import com.example.demo.model.Users;

public interface UsersService {

    Users createUser(UserModel user);
    Users getUser(Long id);

    Users updateUser(UserModel user, Long id);

    void deleteUser(Long id);

    String getUsername(String email);

    Users getLoggedInUser();
}
