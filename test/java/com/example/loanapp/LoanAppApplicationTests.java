package com.example.loanapp;

import com.example.loanapp.data.UserRequest;
import com.example.loanapp.domain.User;
import com.example.loanapp.service.UserService;
import com.example.loanapp.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.util.Arrays;
import java.util.List;

import static javax.management.Query.times;
import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;

@SpringBootTest
class LoanAppApplicationTests {
    @Autowired
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


        User createdUser = userService.createUser(user);
        assertEquals("test@example.com", createdUser.getEmail());
    }
    @Test
    public void testGetAllUsers() {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user));

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());


    }
    @Test
    public void testDeleteUser() {
        doNothing().when(userService).deleteUser(anyLong());

        ResponseEntity<Void> response = userService.deleteUser(1L);

        assertEquals(204, response.getStatusCodeValue());

    }
}
