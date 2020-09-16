package com.example.refactorings;

class Cage {
    private double length;
    private double width;

    public Cage(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getVolume() {
        return width * length;
    }
}
