package com.example.refactorings.ch10;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.*;

public class Ch10Test {
    private int quantity;
    private int itemPrice;

    private void sendAlert() {
        throw new UnsupportedOperationException();
    }

    private void someLaterCode(String found) {
        throw new UnsupportedOperationException();
    }

    void sendAlert(String[] people) {
        if (foundPersion(people).equals(""))
            return;
        sendAlert();
    }

    String foundPersion(String[] people) {
        for (int i = 0; i < people.length; i++) {
            if (people[i].equals("Don")) {
                return "Don";
            }
            if (people[i].equals("John")) {
                return "John";
            }
        }
        return "";
    }

    void checkSecurity(String[] people) {
        sendAlert(people);
        String found = foundPersion(people);
        someLaterCode(found);
    }


    static class Employee {
        private static final int ENGINEER = 0;
        private static final int SALESMAN = 1;
        private static final int MANAGER = 2;

        private int type;
        private double salary;

        private Employee(int type) {
            this.type = type;
        }

        static Employee createEmployee(int type) {
            return new Employee(type);
        }
    }

    @Test
    void replaceConstructorWithFactoryMethod() {
        final Employee engineer = Employee.createEmployee(Employee.ENGINEER);
        final Employee manager = Employee.createEmployee(Employee.MANAGER);
        final Employee salesman = Employee.createEmployee(Employee.SALESMAN);
    }

    class TempRange {
        public int getLow() {
            throw new UnsupportedOperationException();
        }

        public int getHigh() {
            throw new UnsupportedOperationException();
        }
    }

    class Room {
        private TempRange daysTempRange() {
            throw new UnsupportedOperationException();
        }

        boolean withinPlan(HeatingPlan plan) {
            return plan.withinRange(daysTempRange());
        }
    }

    class HeatingPlan {
        boolean withinRange(TempRange roomRange) {
            return (roomRange.getLow() >= _range.getLow()
                    && roomRange.getHigh() <= _range.getHigh());
        }

        private TempRange _range;
    }

    public double getPrice() {
        return discountedPrice();
    }

    private int getBasePrice() {
        return quantity * itemPrice;
    }

    private int getDiscountLevel() {
        return quantity > 100 ? 2 : 1;
    }

    private double discountedPrice() {
        if (getDiscountLevel() == 2)
            return getBasePrice() * 0.1;
        return getBasePrice() * 0.05;
    }

    class Entry {
        Entry(double value, Date chargeDate) {
            value = value;
            chargeDate = chargeDate;
        }

        Date getDate() {
            return chargeDate;
        }

        double getValue() {
            return value;
        }

        private Date chargeDate;
        private double value;
    }

    class Account {
        private int balance;

        int withdraw(int amount) {
            try {
                newWithdraw(amount);
                return 0;
            } catch (BalanceException e) {
                return -1;
            }
        }

        void newWithdraw(int amount) throws BalanceException {
            if (amount > balance)
                throw new BalanceException();

            balance -= amount;
        }

        public boolean canWdraw(int amount) {
            throw new UnsupportedOperationException();
        }
    }

    @Test
    void checkedException() {
        Account account = new Account();
        int amount = 100;

        try {
            account.newWithdraw(amount);
            doTheUsualThing();
        } catch (BalanceException e) {
            handleOverdrawn();
        }
    }

    class BalanceException extends Exception {}


    private void doTheUsualThing() {
        throw new UnsupportedOperationException();
    }

    private void handleOverdrawn() {
        throw new UnsupportedOperationException();
    }

    class Reading {
        private Collection readings;

        public Reading getLastReading(Reading reading) {
            return (Reading) readings.stream()
                                     .reduce((first, second) -> second)
                                     .orElse(null);
        }
    }

    @Test
    void encapsulateDowncast() {
        final Reading reading = new Reading();
        Reading lastReading = reading.getLastReading(reading);
    }
}
