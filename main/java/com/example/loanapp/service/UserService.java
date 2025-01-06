package com.example.loanapp.service;

import com.example.loanapp.data.UserRequest;
import com.example.loanapp.domain.User;

import java.util.List;

public interface UserService {
    User createUser(UserRequest userRequest);
    List<User> getAllUsers();
    User updateUser(Long id);
    void deleteUser(Long id);
    User getUserById(Long id);
}
