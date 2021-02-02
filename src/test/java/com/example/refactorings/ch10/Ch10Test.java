package com.example.refactorings.ch10;

import org.junit.jupiter.api.Test;

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
        int basePrice = quantity * itemPrice;
        int discountLevel = getDiscountLevel();
        double finalPrice = discountedPrice (basePrice);
        return finalPrice;
    }

    private int getDiscountLevel() {
        int discountLevel;
        if (quantity > 100)
            discountLevel = 2;
        else
            discountLevel = 1;
        return discountLevel;
    }

    private double discountedPrice(int basePrice) {
        if (getDiscountLevel() == 2)
            return basePrice * 0.1;
        else
            return basePrice * 0.05;
    }
}
