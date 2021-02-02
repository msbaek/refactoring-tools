package com.example.refactorings.ch10;

import org.junit.jupiter.api.Test;

public class Ch10Test {
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
            int low = daysTempRange().getLow();
            int high = daysTempRange().getHigh();
            return plan.withinRange(low, high, daysTempRange());
        }
    }

    class HeatingPlan {
        boolean withinRange(int low, int high, TempRange roomRange) {
            return (roomRange.getLow() >= _range.getLow()
                    && high <= _range.getHigh());
        }

        private TempRange _range;
    }
}
