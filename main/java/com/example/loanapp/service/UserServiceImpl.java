package com.example.loanapp.service;

import com.example.loanapp.data.UserRequest;
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

    public User createUser(UserRequest userRequest) {
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
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);

    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public User updateUser(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));


        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setPassword(user.getPassword());
        return userRepository.save(existingUser);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
