package com.example.refactorings;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

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

class Currency {
    private String code;

    public String getCode() {
        return code;
    }

    private Currency(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(code, currency.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}

public class OrganizingDataTest {
    class Performance {
        private String name;
        private int wins;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWins() {
            return wins;
        }

        public void setWins(String wins) {
            this.wins = Integer.parseInt(wins);
        }
    }

    @Test
    void replaceArrayWithObject() {
        Performance row = new Performance();
        row.setName("Liverpool");
        row.setWins("15");

        String name = row.getName();
        int wins = row.getWins();
    }
}
