package com.example.planManagement.service;

import com.example.planManagement.dto.Login;
import com.example.planManagement.entity.Customer;
import com.example.planManagement.entity.Users;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<String> save(Users users);

    ResponseEntity<String> login(Login loginrequest);

    ResponseEntity<Customer> getProfile(String customerId);

    ResponseEntity<String> getCustomerId(Login loginrequest);
}
