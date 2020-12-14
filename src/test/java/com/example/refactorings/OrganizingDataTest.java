package com.example.refactorings;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

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

    class SymFocus extends FocusAdapter implements Observer {
        private Interval subject;
        private TextField startField;
        private TextField endField;
        private TextField lengthField;

        public SymFocus() {
            subject = new Interval();
            subject.addObserver(this);
            update(subject, null);
        }

        @Override
        public void update(Observable o, Object arg) {
            startField.setText(subject.getStart());
            endField.setText(subject.getEnd());
            lengthField.setText(subject.getLength());
        }

        public void focusLost(FocusEvent event) {
            Object object = event.getSource();
            if (object == getStartField())
                StartField_FocusLost(event);
            else if (object == getEndField())
                EndField_FocusLost(event);
            else if (object == getLengthField())
                LengthField_FocusLost(event);
        }

        private void StartField_FocusLost(FocusEvent event) {
            if (isNotInteger(getStartField()))
                setStartField("0");
            calculateLength();
        }

        private void EndField_FocusLost(FocusEvent event) {
            if (isNotInteger(getEndField()))
                setEndField("0");
            calculateLength();
        }

        private void LengthField_FocusLost(FocusEvent event) {
            if (isNotInteger(getLengthField()))
                setLengthField("0");
            calculateEnd();
        }

        private boolean isNotInteger(String text) {
            throw new UnsupportedOperationException();
        }

        private void calculateLength() {
            try {
                int start = Integer.parseInt(getStartField());
                int end = Integer.parseInt(getEndField());
                int length = end - start;
                setLengthField(String.valueOf(length));
            } catch (NumberFormatException e) {
                throw new RuntimeException("Unexpected Number Format Error");
            }
        }

        private void calculateEnd() {
            try {
                int start = Integer.parseInt(getStartField());
                int length = Integer.parseInt(getLengthField());
                int end = start + length;
                setEndField(String.valueOf(end));
            } catch (NumberFormatException e) {
                throw new RuntimeException("Unexpected Number Format Error");
            }
        }

        public String getStartField() {
            return subject.getStart();
        }

        public void setStartField(String startField) {
            subject.setStart(startField);
        }

        public String getEndField() {
            return subject.getEnd();
        }

        public void setEndField(String endField) {
            subject.setEnd(endField);
        }

        public String getLengthField() {
            return subject.getLength();
        }

        public void setLengthField(String lengthField) {
            subject.setLength(lengthField);
        }
    }

    class Interval extends Observable {
        private String start;
        private String end;
        private String length;

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
            super.setChanged();
            notifyObservers();
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }
    }
}
