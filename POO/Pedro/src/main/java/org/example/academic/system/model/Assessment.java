package org.example.academic.system.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Abstract base class for all assessment types.
 * Uses Lombok to reduce boilerplate (TUS-2365).
 * Validated via Jakarta Bean Validation (TUS-2371).
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public abstract class Assessment {

    @NotNull(message = "Assessment value must not be null")
    @DecimalMin(value = "0.0", message = "Assessment value must be >= 0")
    @DecimalMax(value = "10.0", message = "Assessment value must be <= 10")
    private Double value;

    @NotNull(message = "Assessment weight must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Assessment weight must be > 0")
    @DecimalMax(value = "1.0", message = "Assessment weight must be <= 1.0")
    private Double weight;

    public Assessment(Double value, Double weight) {
        this.value = value;
        this.weight = weight;
    }

    public abstract String getType();
}
