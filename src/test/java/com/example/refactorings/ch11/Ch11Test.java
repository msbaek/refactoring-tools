package com.example.refactorings.ch11;

import java.time.LocalDate;

public class Ch11Test {
    class Customer {
        private LocalDate billDate;

        public void addBill(LocalDate date, Double amount) {
        }

        public LocalDate lastBillDate() {
            return billDate;
        }
    }

    class RegularCustomer extends Customer {
        public void createBill(LocalDate date) {
            double chargeAmount = chargeFor(lastBillDate(), date);
            addBill(date, chargeAmount);
        }

        private double chargeFor(LocalDate lastBillDate, LocalDate date) {
            return 1;
        }
    }

    class PreferredCustomer extends Customer {
        public void createBill(LocalDate date) {
            double chargeAmount = chargeFor(lastBillDate(), date);
            addBill(date, chargeAmount);
        }

        private double chargeFor(LocalDate lastBillDate, LocalDate date) {
            return 2;
        }
    }
}