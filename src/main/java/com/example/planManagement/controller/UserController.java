package com.example.planManagement.controller;

import com.example.planManagement.dto.Login;
import com.example.planManagement.entity.Customer;
import com.example.planManagement.entity.SimCard;
import com.example.planManagement.entity.Users;
import com.example.planManagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/")
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    //This API is used to save the user after completing the entering of details
    //This is the final step in user sign up.
    @PostMapping("saveUser")
    public ResponseEntity<String> saveUser(@RequestBody Users users){
        return userService.save(users);
    }

    //This is used to login our user.
    //Remember our old logic.
    //We either sign in using email/phone number and we will send both the values in parameter email
    //DTO is used here so map values accordingly.
    @PostMapping("Login")
    public ResponseEntity<String> login(@RequestBody Login loginrequest){
        return userService.login(loginrequest);
    }

    @GetMapping(value ="/profile/{customerId}")
    public ResponseEntity<Customer> getProfile(@PathVariable String customerId){
        return userService.getProfile(customerId);
    }

}
