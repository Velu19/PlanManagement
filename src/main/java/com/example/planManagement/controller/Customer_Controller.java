package com.example.planManagement.controller;

import com.example.planManagement.dto.Login;
import com.example.planManagement.entity.RouterPlan;
import com.example.planManagement.entity.SimCard;
import com.example.planManagement.service.CustomerService;
import com.example.planManagement.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "api/v1/")
@CrossOrigin
public class Customer_Controller {

    private final CustomerService customerService;

    @Autowired
    public Customer_Controller(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping(value = "getall")
    public ResponseEntity<List<Customer>> getall(){
        List<Customer> customers = customerService.Findall();
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }

    //This method is to validate the customer to provide OTP generation which is not done till now.
    @PostMapping("validation")
    public ResponseEntity<String> customerValidation(@RequestBody Customer customer){
        return customerService.customerValidation(customer);
    }

    @PostMapping("values")
    public ResponseEntity<Customer> fronEndValueSet(@RequestBody Customer customer){
        return customerService.setFrontValues(customer);
    }

//    @PostMapping("getRouterPlans")
//    public ResponseEntity<List<RouterPlan>> getPlans(@RequestBody Login login){
//        return customerService.getPlans(login);
//    }

    @GetMapping("{customerId}/routerPlans")
    public ResponseEntity<List<RouterPlan>> getCustomerWithRouter(@PathVariable String customerId) {
        return customerService.getCustomerWithRouterPlans(customerId);
    }

    @GetMapping("{customerId}/simCard")
    public ResponseEntity<List<SimCard>> getCustomerWithSimCard(@PathVariable String customerId) {
        return customerService.getCustomerWithSimCard(customerId);
    }

//    @GetMapping("{customerId}/SimcardWithPlans")
//    public ResponseEntity<>


}
