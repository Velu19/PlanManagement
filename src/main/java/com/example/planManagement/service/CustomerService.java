package com.example.planManagement.service;

import com.example.planManagement.dto.Login;
import com.example.planManagement.entity.Customer;
import com.example.planManagement.entity.RouterPlan;
import com.example.planManagement.entity.SimCard;
import com.example.planManagement.entity.Users;
import com.example.planManagement.repository.Customer_Repository;
import com.example.planManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    private final Customer_Repository customerRepository;
//    private final UserRepository userRepository;

    @Autowired
    public CustomerService(Customer_Repository customerRepository, UserRepository userRepository){
        this.customerRepository = customerRepository;
//        this.userRepository = userRepository;
    }


    public List<Customer> Findall(){
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }


    public ResponseEntity<String> customerValidation(Customer customer) {
        Optional<Customer> check = customerRepository.findById(customer.getId());
        Optional<Customer> check1 = customerRepository.findByName(customer.getName());
        if(check.isPresent()){
            Customer exist = check.get();
            if(exist.getName().equals(customer.getName()))
            {
                return ResponseEntity.ok("Success");
            }
            else if (check1.isPresent()) {
                return (ResponseEntity<String>) ResponseEntity.ok("Account holder name/number is incorrect");
            }
            else{
                return (ResponseEntity<String>) ResponseEntity.ok("Account holder name is wrong");
            }
        }
        else{
            return ResponseEntity.ok("CustomerID is not found");
        }
    }


    public ResponseEntity<Customer> setFrontValues(Customer customer) {
        Optional<Customer> check = customerRepository.findById(customer.getId());
        Customer exist = new Customer();
        if(check.isPresent()){
            exist =check.get();
        }

        return ResponseEntity.ok(Customer
                .builder()
                        .name(exist.getName())
                        .email(exist.getEmail())
                        .phoneNumber(exist.getPhoneNumber())
                .build()
                );
    }

//    public ResponseEntity<List<RouterPlan>> getPlans(Login login) {
//       Optional<Customer> check = customerRepository.findByPhoneNumberOrEmail(login.getEmail());
//       if(check.isPresent()){
//           Customer exist = check.get();
//           List<RouterPlan> routerPlans = exist.getRouterPlans();
//           return ResponseEntity.ok(routerPlans);
//       }
//        return ResponseEntity.notFound().build(); // Return 404 if customer not found
//    }

    public ResponseEntity<List<RouterPlan>> getCustomerWithRouterPlans(String customerId) {
        List<RouterPlan> routerPlans = customerRepository.findRouterPlans(customerId);
        if (routerPlans == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(routerPlans);
    }

    public ResponseEntity<List<SimCard>> getCustomerWithSimCard(String customerId) {
        List<SimCard> simCards = customerRepository.findSimCards(customerId);
        for(SimCard sim : simCards){
            System.out.println(sim.getIccid());
            System.out.println(sim.getPhoneNumber());
        }
        if(simCards == null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(simCards);

    }
}
