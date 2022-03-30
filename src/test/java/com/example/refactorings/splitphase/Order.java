package com.example.refactorings.splitphase;

record Product(double basePrice, double discountThreshold, double discountRate) {
}

record ShippingMethod(double discountThreshold, double discountedFee, Double feePerCase) {
}

record PriceData(double basePrice) {
}

public class Order {
    double priceOrder(Product product, int quantity, ShippingMethod shippingMethod) {
        double basePrice = product.basePrice() * quantity;
        double discount = Math.max(quantity - product.discountThreshold(), 0)
                * product.basePrice() * product.discountRate();
        PriceData priceData = new PriceData(basePrice);
        double price = applyShippingPrice(priceData, quantity, shippingMethod, discount);
        return price;
    }

    private double applyShippingPrice(PriceData priceData, int quantity, ShippingMethod shippingMethod, double discount) {
        double shippingPerCase = (priceData.basePrice() > shippingMethod.discountThreshold())
                ? shippingMethod.discountedFee() : shippingMethod.feePerCase();
        double shippingCost = quantity * shippingPerCase;
        return priceData.basePrice() - discount + shippingCost;
    }
}
