package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repo;

    // create a new customer
    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer (@RequestBody Customer customer){
        return repo.save(customer)
    }

    // update an existing customer
    @PutMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody Customer customer, @PathVariable int id){
        repo.save(album)
    }

    // delete an existing customer
    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id){
        repo.deleteById(id);
    }
    // return a specific customer by id
    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable int id) {
        Optional<Customer> returnVal = repo.findById(id);
        if(returnVal.isPresent()){
            return returnVal.get();
        } else {
            return null;
        }
    }

    // return all customers for a specific state
    @GetMapping("/customers/state/{state}")
    public List<Customer> getCustomerByState(@PathVariable String state) {
        return repo.findByState(state);
    }
}
