package com.example.refactorings;

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
}
