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
}
