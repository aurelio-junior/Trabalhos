package org.example.academic.system.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Assessment {

    private double value;
    private double weight;
}