package org.example.academic.system.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;

@Getter
public abstract class Assessment {

    @DecimalMin(value = "0.0", message = "value must be >= 0")
    @DecimalMax(value = "10.0", message = "value must be <= 10")
    private double value;

    @DecimalMin(value = "0.0", message = "weight must be >= 0")
    @DecimalMax(value = "1.0", message = "weight must be <= 1")
    private double weight;

    public Assessment(double value, double weight) {
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
