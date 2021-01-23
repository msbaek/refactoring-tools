package com.example.refactorings;

public class Ch09Test {
    class Employee {
        private int monthlySalary;
        private int commission;
        private int bonus;

        int payAmount() {
            switch (getType()) {
                case EmployeeType.ENGINEER:
                    return monthlySalary;
                case EmployeeType.SALESMAN:
                    return monthlySalary + commission;
                case EmployeeType.MANAGER:
                    return monthlySalary + bonus;
                default:
                    throw new RuntimeException("Incorrect Employee");
            }
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
