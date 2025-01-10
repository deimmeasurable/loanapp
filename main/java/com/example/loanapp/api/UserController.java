package com.example.loanapp.api;

import com.example.loanapp.data.UserRequest;
import com.example.loanapp.data.UserResponse;
import com.example.loanapp.domain.User;
import com.example.loanapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
//@Api(value = "User Management System", tags = "User Management")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public UserResponse createUser(@RequestBody UserRequest user) {
        UserResponse userResponse= userService.createUser(user);
        return userResponse;
    }
    @PutMapping("/update")

    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest user) {
        UserResponse updatedUser = userService.updateUser( user);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{id}")
   // @ApiOperation(value = "Get a user by ID")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
   // @ApiOperation(value = "Get all users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    //@ApiOperation(value = "Delete a user by ID")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
