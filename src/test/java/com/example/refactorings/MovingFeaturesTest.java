package com.example.refactorings;

class Account1 {
    double bankCharge() {
        double result = 4.5;
        if (_daysOverdrawn > 0)
            result += _type.overdraftCharge(this._daysOverdrawn);
        return result;
    }

    private AccountType _type;
    private int _daysOverdrawn;
}

class AccountType {
    public boolean isPremium() {
        throw new UnsupportedOperationException();
    }

    double overdraftCharge(double daysOverdrawn) {
        if (isPremium()) {
            double result = 10;
            if (daysOverdrawn > 7)
                result += (daysOverdrawn - 7) * 0.85;
            return result;
        }
        else
            return daysOverdrawn * 1.75;
    }

}

class Account {
    private AccountType _type;
    private double _interestRate;

    double interestForAmountDays(double amount, int days) {
        return _interestRate * amount * days / 365;
    }
}

public class MovingFeaturesTest {
}
