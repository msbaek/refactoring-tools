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

    @Test
    void parameterizedMethod() {
        class Employee {
            private double salary;

            void tenPercentRaise(double factor) {
                salary *= factor;
            }

        }

        final Employee e = new Employee();
        e.tenPercentRaise(1.1);
        e.tenPercentRaise(1.05);
    }
}
