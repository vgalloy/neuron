package com.vgalloy.neuron.neuron;

import java.util.List;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public final class SimpleNeuron extends AbstractNeuron {

    public SimpleNeuron(final Double firstCoefficient, final List<Double> coefficients) {
        super(firstCoefficient, coefficients, AggregationFunction.IDENTITY);
    }

    @Override
    public String toString() {
        return "Simple" + getCoefficients();
    }
}
