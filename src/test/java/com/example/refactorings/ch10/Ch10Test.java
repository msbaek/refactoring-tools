package com.example.refactorings.ch10;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    static final int ENGINEER = 0;
    static final int SALESMAN = 1;
    static final int MANAGER = 2;

    static class Employee {
        private double salary;

        void tenPercentRaise(double factor) {
            salary *= factor;
        }

        static Employee createEngineer() {
            return new Engineer();
        }

        static Employee createSalesman() {
            return new Salesman();
        }

        static Employee createManager() {
            return new Manager();
        }

        private static class Engineer extends Employee {
        }

        private static class Salesman extends Employee {
        }

        private static class Manager extends Employee {
        }
    }

    @Test
    void replaceParameterWithExplicitMethods() {
        Employee kent = Employee.createEngineer();
        Employee beck = Employee.createSalesman();
        Employee bob = Employee.createManager();
    }

    @Test
    void parameterizedMethod() {
        final Employee e = new Employee();
        e.tenPercentRaise(1.1);
        e.tenPercentRaise(1.05);
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
        private List<Entry> entries = new ArrayList();
        double getFlowBetween(DateRange dateRange) {
            double result = 0;
            for (Entry each : entries) {
                if (dateRange.includes(each.getDate())) {
                    result += each.getValue();
                }
            }
            return result;
        }

    }

    @Test
    void introduceParameterObjectClient() {
        Date startDate = null;
        Date endDate = null;
        Account anAccount = new Account();
        double flow = anAccount.getFlowBetween(new DateRange(startDate, endDate));
    }
}
