package com.example.refactorings;

class Middleman {
    public RemoveMiddlemanSample getImpValue() {
        throw new UnsupportedOperationException();
    }
}

public class RemoveMiddlemanSample {
    Middleman middleman;

    public RemoveMiddlemanSample getImpValue() {
        return middleman.getImpValue();
    }
}

class Client {
    RemoveMiddlemanSample a;
    RemoveMiddlemanSample impValue = a.getImpValue();
}
