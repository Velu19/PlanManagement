package com.example.planManagement.controller;

import com.example.planManagement.dto.Login;
import com.example.planManagement.entity.Users;
import com.example.planManagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/")
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService= userService;
    }

    @PostMapping("saveUser")
    public ResponseEntity<String> saveUser(@RequestBody Users users){
        return userService.save(users);
    }

    @PostMapping("Login")
    public ResponseEntity<String> login(@RequestBody Login loginrequest){
        return userService.login(loginrequest);
    }

}
