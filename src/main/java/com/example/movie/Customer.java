package com.example.movie;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        return new TextStatement().invoke();
    }

    public String htmlStatement() {
        return new HtmlStatement().invoke();
    }

    private int getTotalFrequentRenterPoints() {
        return 0;
    }

    private int getTotalCharge() {
        return 0;
    }

    private class TextStatement {
        public String invoke() {
            String result = headerString();
            for (Rental each : rentals) {
                //show figures for this rental
                result += eachRentalString(each);
            }

            //add footer lines
            result += footerString();
            return result;
        }

        private String footerString() {
            return "Amount owed is " + String.valueOf(getTotalCharge()) + "\n" +
                    "You earned " + String.valueOf(getTotalFrequentRenterPoints()) +
                    " frequent renter points";
        }

        private String eachRentalString(Rental each) {
            return "\t" + each.getMovie().getTitle() + "\t" +
                    String.valueOf(each.getCharge()) + "\n";
        }

        private String headerString() {
            return "Rental Record for " + getName() + "\n";
        }
    }

    private class HtmlStatement {
        public String invoke() {
            String result = headerString();
            for (Rental each : rentals) {
                //show figures for each rental
                result += eachRentalString(each);
            }

            //add footer lines
            result += footerString();
            return result;
        }

        private String footerString() {
            return "<P>You owe <EM>" + String.valueOf(getTotalCharge()) + "</EM><P>\n"
                    + "On this rental you earned <EM>" +
                    String.valueOf(getTotalFrequentRenterPoints()) +
                    "</EM> frequent renter points<P>";
        }

        private String eachRentalString(Rental each) {
            return each.getMovie().getTitle() + ": " +
                    String.valueOf(each.getCharge()) + "<BR>\n";
        }

        private String headerString() {
            return "<H1>Rentals for <EM>" + getName() + "</EM></H1><P>\n";
        }
    }
}

