package com.example.planManagement.controller;

import com.example.planManagement.dto.Login;
import com.example.planManagement.dto.SimCardWithPlans;
import com.example.planManagement.entity.Router;
import com.example.planManagement.entity.RouterPlan;
import com.example.planManagement.entity.SimCard;
import com.example.planManagement.service.CustomerService;
import com.example.planManagement.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/")
@CrossOrigin
public class Customer_Controller {

    private final CustomerService customerService;

    @Autowired
    public Customer_Controller(CustomerService customerService){
        this.customerService = customerService;
    }

    //This is the endpoint to get the details of all customers.
    @GetMapping(value = "getall")
    public ResponseEntity<List<Customer>> getall(){
        List<Customer> customers = customerService.Findall();
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }

    //This method is to validate the customer to exist in the database.Used while the first step of sign in
    //we provide the Customer Id and name to verify the customer.
    @PostMapping("validation")
    public ResponseEntity<String> customerValidation(@RequestBody Customer customer){
        return customerService.customerValidation(customer);
    }

    //This API is used to send back the values which will be placed statically in the front end
    //In order to prevent duplication of data.
    @PostMapping("values")
    public ResponseEntity<Customer> fronEndValueSet(@RequestBody Customer customer){
        return customerService.setFrontValues(customer);
    }

    //This API is to get the Router plans offered to a customer.
    //customer ID is a path variable here and we get the contents for plan cards here
    @GetMapping("{customerId}/routerPlans")
    public ResponseEntity<List<RouterPlan>> getCustomerWithRouter(@PathVariable String customerId) {
        return customerService.getCustomerWithRouterPlans(customerId);
    }

    //This API is to get the Sim Cards offered to a customer.
    @GetMapping("{customerId}/simCard")
    public ResponseEntity<List<SimCard>> getCustomerWithSimCard(@PathVariable String customerId) {
        return customerService.getCustomerWithSimCard(customerId);
    }

    @GetMapping("{customerId}/simWithPlans")
    public ResponseEntity<List<SimCardWithPlans>> getCustomerSimCardWPlans(@PathVariable String customerId) {
        return customerService.getSimcardWithPlans(customerId);
    }

    @GetMapping("{uniqueID}/getRouter")
    public ResponseEntity<Router>  getRouterDetails(@PathVariable Integer uniqueID){
        return customerService.getRouterForPlan(uniqueID);
    }



}
