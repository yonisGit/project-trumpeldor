package com.example.bguarts.SharedClasses;

public class AppDifference {

    private int numberDifference;
    private double x;
    private double y;

    public AppDifference(int numberDifference, double x, double y) {
        this.numberDifference = numberDifference;
        this.x = x;
        this.y = y;
    }

    public int getNumberDifference() {
        return numberDifference;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
