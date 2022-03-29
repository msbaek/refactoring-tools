package com.example.refactorings.splitphase;

record Product(double basePrice, double discountThreshold, double discountRate) {
}

record ShippingMethod(double discountThreshold, double discountedFee, Double feePerCase) {
}

public class Order {
    double priceOrder(Product product, int quantity, ShippingMethod shippingMethod) {
        final double basePrice = product.basePrice() * quantity;
        final double discount = Math.max(quantity - product.discountThreshold(), 0)
                * product.basePrice() * product.discountRate();
        final double shippingPerCase = (basePrice > shippingMethod.discountThreshold())
                ? shippingMethod.discountedFee() : shippingMethod.feePerCase();
        final double shippingCost = quantity * shippingPerCase;
        final double price = basePrice - discount + shippingCost;
        return price;
    }
}
