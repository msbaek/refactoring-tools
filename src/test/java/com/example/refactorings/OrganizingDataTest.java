package com.example.refactorings;

import java.util.List;

class Customer {
    private final String name;

    private Customer(String name) {
        this.name = name;
    }

    public static Customer create(String name) {
        return new Customer(name);
    }

    public String getName() {
        return name;
    }
}

class Order {
    public Order(String customerName) {
        customer = Customer.create(customerName);
    }

    public void setCustomer(String customerName) {
        customer = Customer.create(customerName);
    }

    public String getCustomerName() {
        return customer.getName();
    }

    private Customer customer;
}

class ChangeValueToReferenceClient {
    private static int numberOfOrdersFor(List<Order> orders, String customer) {
        int result = 0;
        for (Order each : orders) {
            if (each.getCustomerName()
                    .equals(customer)) result++;
        }
        return result;
    }
}

public class OrganizingDataTest {
}
