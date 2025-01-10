package com.example.loanapp.service;

import com.example.loanapp.data.UserRequest;
import com.example.loanapp.data.UserResponse;
import com.example.loanapp.domain.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    List<User> getAllUsers();
    UserResponse updateUser(UserRequest userRequest);
    UserResponse deleteUser(Long id);
    User getUserById(Long id);
}
