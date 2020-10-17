package com.example.refactorings;

class AccountType {
    public boolean isPremium() {
        throw new UnsupportedOperationException();
    }

    double overdraftCharge(Account account) {
        if (isPremium()) {
            double result = 10;
            if (account._daysOverdrawn > 7)
                result += (account._daysOverdrawn - 7) * 0.85;
            return result;
        }
        else
            return account._daysOverdrawn * 1.75;
    }
}

class Account {

    double bankCharge() {
        double result = 4.5;
        if (_daysOverdrawn > 0)
            result += _type.overdraftCharge(this);
        return result;
    }

    private AccountType _type;
    private int _daysOverdrawn;
}

public class MovingFeaturesTest {
}
