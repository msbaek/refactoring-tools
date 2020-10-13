package com.example.refactorings;

import java.util.List;

public class ComposingMethodsTest {
    private List<Order> orders;
    private String name;

    class Order {
        private double amount;

        public double getAmount() {
            return amount;
        }
    }

    void printOwing() {
        double outstanding = 0.0;

        printBanner();

        // calculate outstanding
        for(Order each : orders) {
            outstanding += each.getAmount();
        }
        printDetails(outstanding);

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
}
