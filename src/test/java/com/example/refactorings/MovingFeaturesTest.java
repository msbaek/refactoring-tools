package com.example.refactorings;

import org.junit.jupiter.api.Test;

import java.util.Date;

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
    private double _interestRate;

    public boolean isPremium() {
        throw new UnsupportedOperationException();
    }

    double overdraftCharge(double daysOverdrawn) {
        if (isPremium()) {
            double result = 10;
            if (daysOverdrawn > 7)
                result += (daysOverdrawn - 7) * 0.85;
            return result;
        } else
            return daysOverdrawn * 1.75;
    }

    public double get_interestRate() {
        return _interestRate;
    }

    public void set_interestRate(double _interestRate) {
        this._interestRate = _interestRate;
    }
}

class Account {
    private AccountType _type;

    double interestForAmountDays(double amount, int days) {
        return get_interestRate() * amount * days / 365;
    }

    public double get_interestRate() {
        return _type.get_interestRate();
    }

    public void set_interestRate(double _interestRate) {
        _type.set_interestRate(_interestRate);
    }
}

public class MovingFeaturesTest {
    @Test
    void introduceForeignMethod() {
        Date previousEnd = new Date();

        Date newStart = new Date(previousEnd.getYear(),
                previousEnd.getMonth(), previousEnd.getDate() + 1);
    }
}

class Person1 {
    private TelephoneNumber officeTelephone = new TelephoneNumber();

    String getOfficeAreaCode() {
        return officeTelephone.getOfficeAreaCode();
    }

    void setOfficeAreaCode(String arg) {
        officeTelephone.setOfficeAreaCode(arg);
    }

    String getOfficeNumber() {
        return officeTelephone.getOfficeNumber();
    }

    void setOfficeNumber(String arg) {
        officeTelephone.setOfficeNumber(arg);
    }

    public String getName() {
        return name;
    }

    public String getTelephoneNumber() {
        return officeTelephone.getPhoneNumber();
    }

    private String name;
}

class TelephoneNumber {
    String getOfficeAreaCode() {
        return officeAreaCode;
    }

    void setOfficeAreaCode(String arg) {
        officeAreaCode = arg;
    }

    String getOfficeNumber() {
        return officeNumber;
    }

    void setOfficeNumber(String arg) {
        officeNumber = arg;
    }

    private String officeAreaCode;
    private String officeNumber;

    String getPhoneNumber() {
        return "(" + getOfficeAreaCode() + ") " + getOfficeNumber();
    }
}

class Person {
    Department _department;

    public Department get_department() {
        return _department;
    }
}

class Department {
    private Person _manager;

    public Department(Person manager) {
        _manager = manager;
    }

    public Person getManager() {
        return _manager;
    }
}

class RemoveMiddleManClient {
    private Person john;
    private Person manager;

    void useManager() {
        manager = john.get_department().getManager();
    }
}

class HideDelegateClient {
    private Person john;
    private Person manager;

    void useManager() {
        manager = john.get_department()
                      .getManager();
    }
}

