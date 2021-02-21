package com.example.refactorings.ch11;

import org.junit.jupiter.api.Test;

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

    class EmployeeOld {
        protected String name;
        protected String id;

        public EmployeeOld(String name, String id) {
            this.name = name;
            this.id = id;
        }
    }

    class Manager extends EmployeeOld {
        public Manager(String name, String id, int grade) {
            super(name, id);
            grade = grade;
        }

        private int grade;
    }

    class JobItem {
        protected JobItem(int unitPrice, int quantity, boolean isLabor, Employee employee) {
            this.unitPrice = unitPrice;
            this.quantity = quantity;
            this.isLabor = isLabor;
            this.employee = employee;
        }

        public JobItem(int unitPrice, int quantity) {
            this.unitPrice = unitPrice;
            this.quantity = quantity;
        }

        public int getTotalPrice() {
            return getUnitPrice() * quantity;
        }

        public int getUnitPrice() {
            return (isLabor) ?
                    employee.getRate() :
                    unitPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        public Employee getEmployee() {
            return employee;
        }

        private int unitPrice;
        private int quantity;
        private Employee employee;
        private boolean isLabor;
    }

    class LaborItem extends JobItem {
        public LaborItem(int unitPrice, int quantity) {
            super(unitPrice, quantity);
        }
    }


    class Employee {
        public Employee(int rate) {
            this.rate = rate;
        }

        public int getRate() {
            return rate;
        }

        private int rate;
    }

    @Test
    void laborItemClient() {
        Employee kent = new Employee(1);
        JobItem jobItem = new LaborItem(0, 5);
    }
}