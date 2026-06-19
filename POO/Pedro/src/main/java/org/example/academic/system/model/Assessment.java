package org.example.academic.system.model;

public abstract class Assessment {

    private final double value;
    private final double weight;

    protected Assessment(double value, double weight) {
        this.value = value;
        this.weight = weight;
    }

    public double getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }
}