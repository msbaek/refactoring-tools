package com.example.refactorings.ch10;

public class Ch10Test {
    private void sendAlert() {
        throw new UnsupportedOperationException();
    }

    private void someLaterCode(String found) {
        throw new UnsupportedOperationException();
    }

    String foundMiscreant(String[] people){
        for (int i = 0; i < people.length; i++) {
            if (people[i].equals ("Don")){
                sendAlert();
                return foundPersion(people);
            }
            if (people[i].equals ("John")){
                sendAlert();
                return foundPersion(people);
            }
        }
        return foundPersion(people);
    }

    String foundPersion(String[] people){
        for (int i = 0; i < people.length; i++) {
            if (people[i].equals ("Don")){
                return "Don";
            }
            if (people[i].equals ("John")){
                return "John";
            }
        }
        return "";
    }

    void checkSecurity(String[] people) {
        foundMiscreant(people);
        String found = foundPersion(people);
        someLaterCode(found);
    }
}
