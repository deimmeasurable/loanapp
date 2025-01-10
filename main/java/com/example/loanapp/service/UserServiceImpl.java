package com.example.loanapp.service;

import com.example.loanapp.data.UserRequest;
import com.example.loanapp.data.UserResponse;
import com.example.loanapp.domain.User;
import com.example.loanapp.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.loanapp.config.Validator.isValidEmail;
import static com.example.loanapp.config.Validator.isValidPhoneNumber;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserResponse createUser(UserRequest userRequest) {
        userRequest.setId(userRequest.getId());
        userRequest.setPhoneNumber(userRequest.getPhoneNumber());
        userRequest.setEmail(userRequest.getEmail());
        userRequest.setPassword(userRequest.getPassword());
        isValidEmail(userRequest.getEmail());
        isValidPhoneNumber(userRequest.getPhoneNumber());

        User user =new User();
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("user created");
        return userResponse;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);

    }

    public UserResponse deleteUser(Long id) {
        userRepository.deleteById(id);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("user deleted");
        return userResponse;
    }
    public UserResponse updateUser(UserRequest userRequest) {
        User existingUser = userRepository.findById(userRequest.getId()).orElseThrow(() -> new RuntimeException("User not found"));
UserRequest user=new UserRequest();
user.setId(userRequest.getId());
user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(user.getPhoneNumber());
user.setPassword(userRequest.getPassword());

        existingUser.setId(user.getId());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(existingUser);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("user updated");
        return userResponse;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
