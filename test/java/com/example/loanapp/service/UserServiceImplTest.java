package com.example.loanapp.service;

import com.example.loanapp.data.UserRequest;
import com.example.loanapp.data.UserResponse;
import com.example.loanapp.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private  UserService userService;

    UserServiceImplTest(UserService userService) {
        this.userService = userService;
    }

    @BeforeEach
    void setUp() {
        userService=new UserServiceImpl();
    }
    @Test
    public void testCreateUser() {
        UserRequest user = new UserRequest();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setPassword("07064532412");


        UserResponse createdUser = userService.createUser(user);
        Assertions.assertEquals("user created", createdUser.getMessage());
    }
    @Test
    public void testGetAllUsers() {

        List<User> response = userService.getAllUsers();


        assertEquals(1, response.size());


    }
    @Test
    public void testDeleteUser() {


        UserResponse response = userService.deleteUser(1L);

        assertEquals("user deleted", response);

    }
}