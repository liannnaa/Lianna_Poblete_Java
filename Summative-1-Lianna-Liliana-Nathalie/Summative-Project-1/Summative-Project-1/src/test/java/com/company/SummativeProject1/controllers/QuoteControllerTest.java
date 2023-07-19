package com.company.SummativeProject1.controllers;

import com.company.SummativeProject1.controller.QuoteController;
import com.company.SummativeProject1.model.Quote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuoteController.class)
    public class QuoteControllerTest {

        @Autowired
        private MockMvc mockMvc;
        private ObjectMapper mapper = new ObjectMapper();
        private List<Quote> quoteList;
        @BeforeEach
        public void setUp(){

        }

        @Test
        public void shouldReturnRandomQuote() throws Exception {

            // ARRANGE
            // Convert Java object to JSON
            String outputJson = mapper.writeValueAsString(quoteList);

            // ACT
            mockMvc.perform(get("/quote"))                // Perform the GET request
                    .andDo(print())                          // Print results to console
                    .andExpect(status().isOk());              // ASSERT (status code is 200)
        }
    }
