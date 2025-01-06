package com.example.loanapp.api;

import com.example.loanapp.data.UserRequest;
import com.example.loanapp.domain.User;
import com.example.loanapp.service.UserService;
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User createUser(@RequestBody UserRequest user) {
        return userService.createUser(user);
    }
}
