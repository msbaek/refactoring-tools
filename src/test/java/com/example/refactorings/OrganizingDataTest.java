package com.example.refactorings;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

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

    class SymFocus extends FocusAdapter {
        private Interval subject;
        private TextField startField;
        private TextField endField;
        private TextField lengthField;

        public void focusLost(FocusEvent event) {
            Object object = event.getSource();
            if (object == startField)
                StartField_FocusLost(event);
            else if (object == endField)
                EndField_FocusLost(event);
            else if (object == lengthField)
                LengthField_FocusLost(event);
        }

        private void StartField_FocusLost(FocusEvent event) {
            if (isNotInteger(startField.getText()))
                startField.setText("0");
            calculateLength();
        }

        private void EndField_FocusLost(FocusEvent event) {
            if (isNotInteger(endField.getText()))
                endField.setText("0");
            calculateLength();
        }

        private void LengthField_FocusLost(FocusEvent event) {
            if (isNotInteger(lengthField.getText()))
                lengthField.setText("0");
            calculateEnd();
        }

        private boolean isNotInteger(String text) {
            throw new UnsupportedOperationException();
        }

        private void calculateLength() {
            try {
                int start = Integer.parseInt(startField.getText());
                int end = Integer.parseInt(endField.getText());
                int length = end - start;
                lengthField.setText(String.valueOf(length));
            } catch (NumberFormatException e) {
                throw new RuntimeException("Unexpected Number Format Error");
            }
        }

        private void calculateEnd() {
            try {
                int start = Integer.parseInt(startField.getText());
                int length = Integer.parseInt(lengthField.getText());
                int end = start + length;
                endField.setText(String.valueOf(end));
            } catch (NumberFormatException e) {
                throw new RuntimeException("Unexpected Number Format Error");
            }
        }
    }

    class Interval extends Observable {
    }
}
