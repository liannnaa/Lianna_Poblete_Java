package com.company.customerdataservice.repository;

import com.company.customerdataservice.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerRepositoryTests {
    @Autowired
    CustomerRepository repo;

    private Customer customer;

    @BeforeEach
    public void setUp() throws Exception {
        repo.deleteAll();

        //Arrange...
        customer = new Customer();

        customer.setFirstName("Joe");
        customer.setLastName("Smith");

        customer.setEmail("Joe@joe.com");
        customer.setCompany("BigCo");
        customer.setPhone("111-222-3456");

        customer.setAddress1("123 Sesame Street");
        customer.setAddress2("123 Main Street");

        customer.setCity("New York");
        customer.setState("NY");
        customer.setPostalCode("11111");
        customer.setCountry("United States");
    }

    @Test
    public void addCustomer() {
        //Act...
        customer = repo.save(customer);

        //Assert...
        Optional<Customer> customer1 = repo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }

    @Test
    public void updateCustomer() {
        //Arrange...
        repo.save(customer);

        //Act...
        customer.setFirstName("UPDATED");

        repo.save(customer);

        //Assert...
        Optional<Customer> customer1 = repo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }

    @Test
    public void deleteCustomer() {
        //Arrange...
        repo.save(customer);

        //Act...
        repo.deleteById(customer.getId());

        //Assert...
        Optional<Customer> customer1 = repo.findById(customer.getId());

        assertFalse(customer1.isPresent());
    }

    @Test
    public void getCustomerById() {
        //Arrange...
        customer = repo.save(customer);

        //Act...
        Customer customer1 = repo.findById(customer.getId()).orElse(null);

        //Assert...
        assertEquals(customer, customer1);
    }

    @Test
    public void getCustomerByState() {
        //Arrange...
        repo.save(customer);

        Customer customer2 = new Customer();

        customer2.setFirstName("Tom");
        customer2.setLastName("Cruise");
        customer2.setState("CA");
        repo.save(customer2);

        //Act...
        List<Customer> customers = repo.findByState("NY");

        //Assert...
        assertTrue(customers.contains(customer));
        assertTrue(!customers.contains(customer2));
    }
}
