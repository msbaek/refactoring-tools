package com.example.refactorings;

import java.util.List;

class Customer {
    private final String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Order {
    public Order(String customerName) {
        customer = new Customer(customerName);
    }

    public void setCustomer(String customerName) {
        customer = new Customer(customerName);
    }

    public String getCustomerName() {
        return customer.getName();
    }

    private Customer customer;
}

public class OrganizingDataTest {
    private static int numberOfOrdersFor(List<Order> orders, String customer) {
        int result = 0;
        for (Order each : orders) {
            if (each.getCustomerName()
                    .equals(customer)) result++;
        }
        return result;
    }
}
