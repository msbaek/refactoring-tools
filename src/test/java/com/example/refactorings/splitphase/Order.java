package com.example.refactorings.splitphase;

record Product(double basePrice, double discountThreshold, double discountRate) {
}

record ShippingMethod(double discountThreshold, double discountedFee, Double feePerCase) {
}

record PriceData(double basePrice, int quantity, double discount) {
}

public class Order {
    double priceOrder(Product product, int quantity, ShippingMethod shippingMethod) {
        double basePrice = product.basePrice() * quantity;
        double discount = Math.max(quantity - product.discountThreshold(), 0)
                * product.basePrice() * product.discountRate();
        PriceData priceData = new PriceData(basePrice, quantity, discount);
        double price = applyShippingPrice(priceData, shippingMethod);
        return price;
    }

    private double applyShippingPrice(PriceData priceData, ShippingMethod shippingMethod) {
        double shippingPerCase = (priceData.basePrice() > shippingMethod.discountThreshold())
                ? shippingMethod.discountedFee() : shippingMethod.feePerCase();
        double shippingCost = priceData.quantity() * shippingPerCase;
        return priceData.basePrice() - priceData.discount() + shippingCost;
    }
}
