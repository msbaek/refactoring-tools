package com.example.refactorings.ch11;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        protected JobItem(int unitPrice, int quantity, boolean isLabor) {
            this.unitPrice = unitPrice;
            this.quantity = quantity;
            this.setLabor(isLabor);
        }

        public JobItem(int unitPrice, int quantity) {
            this.unitPrice = unitPrice;
            this.quantity = quantity;
        }

        public int getTotalPrice() {
            return getUnitPrice() * quantity;
        }

        public int getUnitPrice() {
            return unitPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        private int unitPrice;
        private int quantity;
        protected EmployeeOld1 employee;

        protected boolean isLabor() {
            return false;
        }

        public void setLabor(boolean labor) {
        }
    }

    class LaborItem extends JobItem {
        public LaborItem(int unitPrice, int quantity, EmployeeOld1 employee) {
            super(unitPrice, quantity);
        }

        public int getUnitPrice() {
            return employee.getRate();
        }

        public EmployeeOld1 getEmployee() {
            return employee;
        }

        protected boolean isLabor() {
            return true;
        }
    }


    class EmployeeOld1 {
        public EmployeeOld1(int rate) {
            this.rate = rate;
        }

        public int getRate() {
            return rate;
        }

        private int rate;
    }

    @Test
    void laborItemClient() {
        EmployeeOld1 kent = new EmployeeOld1(1);
        JobItem jobItem = new LaborItem(0, 5, kent);
    }

    class Employee extends Party {
        public Employee(String name, String id, int annualCost) {
            super(name);
            this.id = id;
            this.annualCost = annualCost;
        }

        @Override
        public int getAnnualCost() {
            return annualCost;
        }

        public String getId() {
            return id;
        }

        private int annualCost;
        private String id;
    }

    public class Department {
        public Department(String name) {
            this.name = name;
        }

        public int getTotalAnnualCost() {
            return staff.stream()
                    .mapToInt(e -> e.getAnnualCost())
                    .sum();
        }

        public int getHeadCount() {
            return staff.size();
        }

        public List<Employee> getStaff() {
            return staff;
        }

        public void addStaff(Employee arg) {
            staff.add(arg);
        }

        public String getName() {
            return name;
        }

        private String name;
        private List<Employee> staff = new ArrayList<>();
    }
}