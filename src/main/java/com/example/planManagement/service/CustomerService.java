package com.example.planManagement.service;

import com.example.planManagement.dto.SimCardWithPlans;
import com.example.planManagement.entity.Customer;
import com.example.planManagement.entity.Router;
import com.example.planManagement.entity.RouterPlan;
import com.example.planManagement.entity.SimCard;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    List<Customer> Findall();

    ResponseEntity<String> customerValidation(Customer customer);

    ResponseEntity<Customer> setFrontValues(Customer customer);

    ResponseEntity<List<RouterPlan>> getCustomerWithRouterPlans(String customerId);

    ResponseEntity<List<SimCard>> getCustomerWithSimCard(String customerId);

    ResponseEntity<List<SimCardWithPlans>> getSimcardWithPlans(String customerId);

    ResponseEntity<Router> getRouterForPlan(Integer uniqueID);
}
