package com.example.refactorings.ch11;

import java.time.LocalDate;

public class Ch11Test {
    abstract class Customer {
        protected LocalDate billDate;

        public void addBill(LocalDate date, Double amount) {
        }

        public LocalDate lastBillDate() {
            return billDate;
        }

        public void createBill(LocalDate date) {
            double chargeAmount = chargeFor(lastBillDate(), date);
            addBill(date, chargeAmount);
        }

        protected abstract double chargeFor(LocalDate lastBillDate, LocalDate date);
    }

    class RegularCustomer extends Customer {
        @Override
        protected double chargeFor(LocalDate lastBillDate, LocalDate date) {
            return 1;
        }
    }

    class PreferredCustomer extends Customer {
        public PreferredCustomer() {
            billDate = LocalDate.now();
        }

        @Override
        protected double chargeFor(LocalDate lastBillDate, LocalDate date) {
            return 2;
        }
    }

    class Employee {
        protected String name;
        protected String id;

        public Employee(String name, String id) {
            this.name = name;
            this.id = id;
        }
    }

    class Manager extends Employee {
        public Manager(String name, String id, int grade) {
            super(name, id);
            grade = grade;
        }

        private int grade;
    }
}