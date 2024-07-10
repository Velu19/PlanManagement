package com.example.planManagement.service;

import com.example.planManagement.dto.SimCardWithPlans;
import com.example.planManagement.entity.Customer;
import com.example.planManagement.entity.Router;
import com.example.planManagement.entity.RouterPlan;
import com.example.planManagement.entity.SimCard;
import com.example.planManagement.repository.Customer_Repository;
import com.example.planManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final Customer_Repository customerRepository;
//    private final UserRepository userRepository;

    @Autowired
    public CustomerServiceImpl(Customer_Repository customerRepository, UserRepository userRepository){
        this.customerRepository = customerRepository;
//        this.userRepository = userRepository;
    }

    @Override
    public List<Customer> Findall(){
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    @Override
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

    @Override
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

    @Override
    public ResponseEntity<List<RouterPlan>> getCustomerWithRouterPlans(String customerId) {
        List<RouterPlan> routerPlans = customerRepository.findRouterPlans(customerId);
        if (routerPlans == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(routerPlans);
    }

    @Override
    public ResponseEntity<List<SimCard>> getCustomerWithSimCard(String customerId) {
        List<SimCard> simCards = customerRepository.findSimCards(customerId);
        if(simCards == null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(simCards);

    }

    @Override
    public ResponseEntity<List<SimCardWithPlans>> getSimcardWithPlans(String customerId) {
        List<SimCardWithPlans> SimwithPlans = customerRepository.findSimWithPlans(customerId);
        if(SimwithPlans == null){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(SimwithPlans);
    }

    @Override
    public ResponseEntity<Router> getRouterForPlan(Integer uniqueID) {
        return ResponseEntity.ok(customerRepository.getRouterForPlan(uniqueID));
    }
}
