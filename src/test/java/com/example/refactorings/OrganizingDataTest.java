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
        public String[] row = new String[3];

        public String getName() {
            return row[0];
        }

        public void setName(String name) {
            row[0] = name;
        }

        public int getWins() {
            return Integer.parseInt(row[1]);
        }

        public void setWins(String wins) {
            row[1] = wins;
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
