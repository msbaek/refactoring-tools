package com.example.refactorings.ch10;

public class Ch10Test {
    private void sendAlert() {
        throw new UnsupportedOperationException();
    }

    private void someLaterCode(String found) {
        throw new UnsupportedOperationException();
    }

    void foundMiscreant(String[] people){
        for (int i = 0; i < people.length; i++) {
            if (people[i].equals ("Don")){
                sendAlert();
                return;
            }
            if (people[i].equals ("John")){
                sendAlert();
                return;
            }
        }
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
