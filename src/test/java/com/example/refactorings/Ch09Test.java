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

        private int payAmount(Employee employee) {
            switch (employee.getType()) {
                case ENGINEER:
                    return employee.monthlySalary;
                case SALESMAN:
                    return employee.monthlySalary + employee.commission;
                case MANAGER:
                    return employee.monthlySalary + employee.bonus;
                default:
                    throw new RuntimeException("Incorrect Employee");
            }
        }
    }

    class Engineer extends EmployeeType {
        @Override
        int getTypeCode() {
            return EmployeeType.ENGINEER;
        }
    }

    class Salesman extends EmployeeType {
        @Override
        int getTypeCode() {
            return EmployeeType.SALESMAN;
        }
    }

    class Manager extends EmployeeType {
        @Override
        int getTypeCode() {
            return EmployeeType.SALESMAN;
        }
    }
}
