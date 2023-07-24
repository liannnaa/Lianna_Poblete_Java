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

    @BeforeEach
    public void setUp() throws Exception {
        repo.deleteAll();
    }

    @Test
    public void addCustomer() {
        //Arrange...
        Customer customer = new Customer();

        customer.setFirstName("Joe");
        customer.setLastName("Smith");

        customer.setEmail("Joe@joe.com");
        customer.setCompany("BigCo");
        customer.setPhone("111-222-3456");

        customer.setAddress1("123 Sesame Street");
        customer.setAddress2("123 Main Street");

        customer.setCity("New York");
        customer.setState("New York");
        customer.setPostalCode("11111");
        customer.setCountry("United States");

        //Act...
        customer = repo.save(customer);

        //Assert...
        Optional<Customer> customer1 = repo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }

    @Test
    public void updateCustomer() {
        //Arrange...
        Customer customer = new Customer();

        customer.setFirstName("Joe");
        customer.setLastName("Smith");

        customer.setEmail("Joe@joe.com");
        customer.setCompany("BigCo");
        customer.setPhone("111-222-3456");

        customer.setAddress1("123 Sesame Street");
        customer.setAddress2("123 Main Street");

        customer.setCity("New York");
        customer.setState("New York");
        customer.setPostalCode("11111");
        customer.setCountry("United States");

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
        Customer customer = new Customer();

        customer.setFirstName("Joe");
        customer.setLastName("Smith");

        customer.setEmail("Joe@joe.com");
        customer.setCompany("BigCo");
        customer.setPhone("111-222-3456");

        customer.setAddress1("123 Sesame Street");
        customer.setAddress2("123 Main Street");

        customer.setCity("New York");
        customer.setState("New York");
        customer.setPostalCode("11111");
        customer.setCountry("United States");

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
        Customer customer = new Customer();

        customer.setFirstName("Joe");
        customer.setLastName("Smith");

        customer.setEmail("Joe@joe.com");
        customer.setCompany("BigCo");
        customer.setPhone("111-222-3456");

        customer.setAddress1("123 Sesame Street");
        customer.setAddress2("123 Main Street");

        customer.setCity("New York");
        customer.setState("New York");
        customer.setPostalCode("11111");
        customer.setCountry("United States");

        customer = repo.save(customer);

        //Act...
        Customer customer1 = repo.findById(customer.getId()).orElse(null);

        //Assert...
        assertEquals(customer, customer1);
    }

    @Test
    public void getCustomerByState() {
        //Arrange...
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();

        customer1.setFirstName("Joe");
        customer1.setLastName("Smith");
        customer1.setState("New York");
        repo.save(customer1);

        customer2.setFirstName("Tom");
        customer2.setLastName("Cruise");
        customer2.setState("California");
        repo.save(customer2);

        //Act...
        List<Customer> customers = repo.findByState("New York");

        //Assert...
        assertTrue(customers.contains(customer1));
        assertTrue(!customers.contains(customer2));
    }
}
