package com.example.refactorings;

import java.util.List;

public class ComposingMethodsTest {
    private List<Order> orders;
    private String name;
    private int quantity;
    private int itemPrice;
    private double primaryForce;
    private double mass;
    private int delay;
    private double secondaryForce;

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
        for (Order each : orders) {
            outstanding += each.getAmount();
        }
        return outstanding;
    }

    private void printDetails(double outstanding) {
        //print details
        System.out.println("name:" + name);
        System.out.println("amount" + outstanding);
    }

    private void printBanner() {
        // print banner
        System.out.println("**************************");
        System.out.println("***** Customer Owes ******");
        System.out.println("**************************");
    }

    double getPrice() {
        return basePrice1() * getDiscountFactor();
    }

    private double getDiscountFactor() {
        final double discountFactor;

        if (basePrice1() > 1000)
            discountFactor = 0.95;
        else
            discountFactor = 0.98;
        return discountFactor;
    }

    private int basePrice1() {
        return basePrice();
    }

    double price() {
        return basePrice() - quantityDiscount() + shipping();
    }

    private double shipping() {
        return Math.min(quantity * itemPrice * 0.1, 100.0);
    }

    private double quantityDiscount() {
        return Math.max(0, quantity - 500) * itemPrice * 0.05;
    }

    private int basePrice() {
        return quantity * itemPrice;
    }

    double getDistanceTravelled(int time) {
        double result;
        final double primaryAcc = primaryForce / mass;
        int primaryTime = Math.min(time, delay);
        result = 0.5 * primaryAcc * primaryTime * primaryTime;
        int secondaryTime = time - delay;
        if (secondaryTime > 0) {
            double primaryVel = primaryAcc * delay;
            double acc = (primaryForce + secondaryForce) / mass;
            result += primaryVel * secondaryTime + 0.5 * acc * secondaryTime * secondaryTime;
        }
        return result;
    }
}
