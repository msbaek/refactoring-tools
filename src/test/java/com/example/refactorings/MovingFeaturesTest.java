package com.example.refactorings;

class AccountType {
    public boolean isPremium() {
        throw new UnsupportedOperationException();
    }
}

class Account {
    double overdraftCharge() {
        if (_type.isPremium()) {
            double result = 10;
            if (_daysOverdrawn > 7)
                result += (_daysOverdrawn - 7) * 0.85;
            return result;
        }
        else
            return _daysOverdrawn * 1.75;
    }

    double bankCharge() {
        double result = 4.5;
        if (_daysOverdrawn > 0)
            result += overdraftCharge();
        return result;
    }

    private AccountType _type;
    private int _daysOverdrawn;
}

public class MovingFeaturesTest {
}
