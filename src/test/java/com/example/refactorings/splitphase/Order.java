package com.example.refactorings.splitphase;

record Product(double basePrice, double discountThreshold, double discountRate) {
}

record ShippingMethod(double discountThreshold, double discountedFee, Double feePerCase) {
}

public class Order {
    double priceOrder(Product product, int quantity, ShippingMethod shippingMethod) {
        double basePrice = product.basePrice() * quantity;
        double discount = Math.max(quantity - product.discountThreshold(), 0)
                * product.basePrice() * product.discountRate();
        double price = applyShippingPrice(quantity, shippingMethod, basePrice, discount);
        return price;
    }

    private double applyShippingPrice(int quantity, ShippingMethod shippingMethod, double basePrice, double discount) {
        double shippingPerCase = (basePrice > shippingMethod.discountThreshold())
                ? shippingMethod.discountedFee() : shippingMethod.feePerCase();
        double shippingCost = quantity * shippingPerCase;
        return basePrice - discount + shippingCost;
    }
}
