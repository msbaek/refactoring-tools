package com.example.refactorings.ch11;

import java.time.LocalDate;

public class Ch11Test {
    abstract class Customer {
        private LocalDate billDate;

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
        public void createBill(LocalDate date) {
            super.createBill(date);
        }

        @Override
        protected double chargeFor(LocalDate lastBillDate, LocalDate date) {
            return 2;
        }
    }
}