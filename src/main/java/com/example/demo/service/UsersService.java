package com.example.demo.service;

import com.example.demo.model.UserModel;
import com.example.demo.model.Users;

public interface UsersService {

    Users createUser(UserModel user);
    Users getUser();

    Users updateUser(UserModel user);

    void deleteUser();

    String getUsername(String email);

    Users getLoggedInUser();
}
