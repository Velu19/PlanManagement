package com.example.planManagement.service;

import com.example.planManagement.dto.Login;
import com.example.planManagement.entity.Users;
import com.example.planManagement.repository.Customer_Repository;
import com.example.planManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final Customer_Repository customerRepository;



    public ResponseEntity<String> save(Users users) {
        userRepository.save(users);
        return ResponseEntity.ok("User has been created");
    }

    public ResponseEntity<String> login(Login loginrequest){
        Optional<Users> check = userRepository.findByPhoneNumberOrEmail(loginrequest.getEmail());
        if(check.isPresent()){
            Users exist = check.get();
            if(exist.getPassword().equals(loginrequest.getPassword())){
                return ResponseEntity.ok(customerRepository.findByPhoneNumberOrEmail(loginrequest.getEmail()).get().getId());
            }
            else {
                return (ResponseEntity<String>) ResponseEntity.ok("password does not match");
            }
        }
        else {
                return (ResponseEntity<String>) ResponseEntity.ok("phone/email does not exist");
        }
    }


}
