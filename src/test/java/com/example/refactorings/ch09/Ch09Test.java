package com.example.refactorings.ch09;

import org.junit.jupiter.api.Test;

public class Ch09Test {
    class Employee {
        private int monthlySalary;
        private int commission;
        private int bonus;

        int payAmount() {
            return type.payAmount(this);
        }

        private int getType() {
            return type.getTypeCode();
        }

        private EmployeeType type;

    }

    abstract class EmployeeType {
        public static final int ENGINEER = 1;
        public static final int SALESMAN = 2;
        public static final int MANAGER = 3;

        abstract int getTypeCode();

        protected abstract int payAmount(Employee employee);
    }

    class Engineer extends EmployeeType {
        @Override
        int getTypeCode() {
            return EmployeeType.ENGINEER;
        }

        @Override
        protected int payAmount(Employee employee) {
            return employee.monthlySalary;
        }
    }

    class Salesman extends EmployeeType {
        @Override
        int getTypeCode() {
            return EmployeeType.SALESMAN;
        }

        @Override
        protected int payAmount(Employee employee) {
            return employee.monthlySalary + employee.commission;
        }
    }

    class Manager extends EmployeeType {
        @Override
        int getTypeCode() {
            return EmployeeType.SALESMAN;
        }

        @Override
        protected int payAmount(Employee employee) {
            return employee.monthlySalary + employee.bonus;
        }
    }

    class Site {
        Customer getCustomer() {
            return customer;
        }

        Customer customer;
    }

    static class BillingPlan {

        public static BillingPlan basic() {
            throw new UnsupportedOperationException();
        }
    }

    class Customer {
        public BillingPlan getPlan() {
            throw new UnsupportedOperationException();
        }

        public String getName() {
            throw new UnsupportedOperationException();
        }

        public int getWeeksDelinquentInLastYear() {
            throw new UnsupportedOperationException();
        }
    }

    @Test
    void nullObjectClientTest() {
        Site site = new Site();
        final Customer customer = site.getCustomer();
        BillingPlan plan;
        if(customer == null) plan = BillingPlan.basic();
        else plan = customer.getPlan();

        String customerName;
        if(customer == null) customerName = "occupant";
        else customerName = customer.getName();

        int weekDelinquent;
        if(customer == null) weekDelinquent = 0;
        else weekDelinquent = customer.getWeeksDelinquentInLastYear();
    }
}
