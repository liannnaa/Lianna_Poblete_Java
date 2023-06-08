package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static List<String[]> customerData = Arrays.asList(
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","-7500","01-10-2022"},
            new String[] {"1","Wayne Enterprises","18000","12-22-2021"},
            new String[] {"3","Ace Chemical","-48000","01-10-2022"},
            new String[] {"3","Ace Chemical","-95000","12-15-2021"},
            new String[] {"1","Wayne Enterprises","175000","01-01-2022"},
            new String[] {"1","Wayne Enterprises","-35000","12-09-2021"},
            new String[] {"1","Wayne Enterprises","-64000","01-17-2022"},
            new String[] {"3","Ace Chemical","70000","12-29-2022"},
            new String[] {"2","Daily Planet","56000","12-13-2022"},
            new String[] {"2","Daily Planet","-33000","01-07-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","33000","01-17-2022"},
            new String[] {"3","Ace Chemical","140000","01-25-2022"},
            new String[] {"2","Daily Planet","5000","12-12-2022"},
            new String[] {"3","Ace Chemical","-82000","01-03-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"}
    );

    public static void main(String[] args) {
        Map<Integer, Customer> customers = new HashMap<>();

        for(String[] record : customerData){
            int id = Integer.parseInt(record[0]);
            String name = record[1];
            int charge = Integer.parseInt(record[2]);
            String chargeDate = record[3];

            Customer customer;
            if(customers.containsKey(id)) {
                customer = customers.get(id);
            } else {
                customer = new Customer();
                customer.setId(id);
                customer.setName(name);

                customers.put(id, customer);
            }

            AccountRecord account = new AccountRecord();
            account.setCharge(charge);
            account.setChargeDate(chargeDate);

            customer.getCharges().add(account);
        }

        List<Customer> uniqueCustomers = new ArrayList<>(customers.values());

        System.out.println("Positive accounts:");
        System.out.println("Negative accounts:");
    }
}
