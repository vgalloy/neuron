package com.vgalloy.neuron.neuron;

import java.util.Arrays;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public final class SimpleNeuron extends AbstractNeuron {

    public SimpleNeuron(final double firstCoefficient, final double... coefficients) {
        super(firstCoefficient, AggregationFunction.IDENTITY, coefficients);
    }

    @Override
    public String toString() {
        return "Simple" + Arrays.toString(getCoefficients());
    }
}
