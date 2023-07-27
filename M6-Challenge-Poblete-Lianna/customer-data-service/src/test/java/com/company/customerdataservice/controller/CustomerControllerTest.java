package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private CustomerRepository repo;

    private Customer customer;

    @BeforeEach
    public void setup() {
        customer = new Customer();
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
    }

    @Test
    public void addCustomerTest() throws Exception {
        when(repo.save(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/customers")
                        .content(mapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Joe"))
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.email").value("Joe@joe.com"))
                .andExpect(jsonPath("$.company").value("BigCo"))
                .andExpect(jsonPath("$.phone").value("111-222-3456"))
                .andExpect(jsonPath("$.address1").value("123 Sesame Street"))
                .andExpect(jsonPath("$.address2").value("123 Main Street"))
                .andExpect(jsonPath("$.city").value("New York"))
                .andExpect(jsonPath("$.state").value("New York"))
                .andExpect(jsonPath("$.postalCode").value("11111"))
                .andExpect(jsonPath("$.country").value("United States"));
    }

    @Test
    public void updateCustomerTest() throws Exception {
        when(repo.save(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/customers")
                        .content(mapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteCustomerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/customers/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getCustomerByIdTest() throws Exception {
        when(repo.findById(1)).thenReturn(Optional.of(customer));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/customers/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Joe"))
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.email").value("Joe@joe.com"))
                .andExpect(jsonPath("$.company").value("BigCo"))
                .andExpect(jsonPath("$.phone").value("111-222-3456"))
                .andExpect(jsonPath("$.address1").value("123 Sesame Street"))
                .andExpect(jsonPath("$.address2").value("123 Main Street"))
                .andExpect(jsonPath("$.city").value("New York"))
                .andExpect(jsonPath("$.state").value("New York"))
                .andExpect(jsonPath("$.postalCode").value("11111"))
                .andExpect(jsonPath("$.country").value("United States"));
    }

    @Test
    public void getCustomerByStateTest() throws Exception {
        when(repo.findByState("New York")).thenReturn(Arrays.asList(customer));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/customers/state/{state}", "New York"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Joe"))
                .andExpect(jsonPath("$[0].lastName").value("Smith"))
                .andExpect(jsonPath("$[0].email").value("Joe@joe.com"))
                .andExpect(jsonPath("$[0].company").value("BigCo"))
                .andExpect(jsonPath("$[0].phone").value("111-222-3456"))
                .andExpect(jsonPath("$[0].address1").value("123 Sesame Street"))
                .andExpect(jsonPath("$[0].address2").value("123 Main Street"))
                .andExpect(jsonPath("$[0].city").value("New York"))
                .andExpect(jsonPath("$[0].state").value("New York"))
                .andExpect(jsonPath("$[0].postalCode").value("11111"))
                .andExpect(jsonPath("$[0].country").value("United States"));
    }
}

