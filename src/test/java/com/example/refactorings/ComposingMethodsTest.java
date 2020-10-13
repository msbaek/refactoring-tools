package com.example.refactorings;

import java.util.List;

public class ComposingMethodsTest {
    private List<Order> orders;
    private String name;
    private int quantity;
    private int itemPrice;

    class Order {
        private double amount;

        public double getAmount() {
            return amount;
        }
    }

    void printOwing() {
        printBanner();
        double outstanding = getOutstanding();
        printDetails(outstanding);

    }

    private double getOutstanding() {
        double outstanding = 0.0;
        // calculate outstanding
        for(Order each : orders) {
            outstanding += each.getAmount();
        }
        return outstanding;
    }

    private void printDetails(double outstanding) {
        //print details
        System.out.println ("name:" + name);
        System.out.println ("amount" + outstanding);
    }

    private void printBanner() {
        // print banner
        System.out.println ("**************************");
        System.out.println ("***** Customer Owes ******");
        System.out.println ("**************************");
    }

    double getPrice() {
        final int basePrice = quantity * itemPrice;
        final double discountFactor;

        if (basePrice > 1000)
            discountFactor = 0.95;
        else
            discountFactor = 0.98;
        return basePrice * discountFactor;
    }
}
