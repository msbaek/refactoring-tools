package com.example.refactorings.ch09;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

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
            return customer == null ? Customer.newNull() : customer;
        }

        Customer customer;
    }

    static class BillingPlan {

        public static BillingPlan basic() {
            throw new UnsupportedOperationException();
        }
    }

    static class Customer {
        public BillingPlan getPlan() {
            throw new UnsupportedOperationException();
        }

        public String getName() {
            throw new UnsupportedOperationException();
        }

        public int getWeeksDelinquentInLastYear() {
            throw new UnsupportedOperationException();
        }

        public boolean isNull() {
            return false;
        }

        public static Customer newNull() {
            return new NullCustomer();
        }
    }

    static class NullCustomer extends Customer {
        @Override
        public boolean isNull() {
            return true;
        }

        @Override
        public BillingPlan getPlan() {
            return BillingPlan.basic();
        }

        @Override
        public String getName() {
            return "occupant";
        }

        @Override
        public int getWeeksDelinquentInLastYear() {
            return 0;
        }
    }

    @Test
    void nullObjectClientTest() {
        Site site = new Site();
        final Customer customer = site.getCustomer();
        BillingPlan plan = customer.getPlan();

        String customerName = customer.getName();

        int weekDelinquent = customer.getWeeksDelinquentInLastYear();
    }

    class Project {
        public double getMemberExpenseLimit() {
            throw new UnsupportedOperationException();
        }
    }

    class OurEmployee {
        private static final double NULL_EXPENSE = -1.0;
        private double _expenseLimit = NULL_EXPENSE;
        private Project _primaryProject;

        double getExpenseLimit() {
            Assert.isTrue(_expenseLimit != NULL_EXPENSE || _primaryProject != null,
                    "should have either expense limit or a primary project");
            return (_expenseLimit != NULL_EXPENSE) ?
                    _expenseLimit:
                    _primaryProject.getMemberExpenseLimit();
        }

        boolean withinLimit(double expenseAmount) {
            return (expenseAmount <= getExpenseLimit());
        }
    }
}
