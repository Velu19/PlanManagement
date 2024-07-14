package com.example.planManagement.service;

import com.example.planManagement.dto.Login;
import com.example.planManagement.entity.Customer;
import com.example.planManagement.entity.Users;
import com.example.planManagement.repository.Customer_Repository;
import com.example.planManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final Customer_Repository customerRepository;


    @Override
    public ResponseEntity<String> save(Users users) {
        userRepository.save(users);
        return ResponseEntity.ok("User has been created");
    }

    @Override
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

    @Override
    public ResponseEntity<Customer> getProfile(String customerId) {
        String phone = customerRepository.findById(customerId).get().getPhoneNumber();
        Optional<Users> check = userRepository.findByPhoneNumber(phone);
        if(check.isPresent()){
            Users exist = check.get();
           return ResponseEntity.ok(
                   Customer.builder()
                   .name(exist.getName())
                   .phoneNumber(exist.getPhoneNumber())
                   .email(exist.getPhoneNumber())
                   .id(customerId)
                   .build()
           );
        }

        return  ResponseEntity.noContent().build();
    }


}
