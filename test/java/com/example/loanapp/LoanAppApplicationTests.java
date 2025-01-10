package com.example.loanapp;

import com.example.loanapp.data.UserRequest;
import com.example.loanapp.data.UserResponse;
import com.example.loanapp.domain.User;
import com.example.loanapp.service.UserService;
import com.example.loanapp.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import javax.annotation.processing.SupportedAnnotationTypes;
import java.util.Arrays;
import java.util.List;


import static org.junit.Assert.assertEquals;

@SpringBootTest
public class LoanAppApplicationTests {

private UserService userService;
    @Test
    void setUp() {
        userService= new UserServiceImpl();
    }
    @Test
    public void testCreateUser() {
        UserRequest user = new UserRequest();
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setPassword("07064532412");


        UserResponse createdUser = userService.createUser(user);
        assertEquals("user created", createdUser.getMessage());
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
