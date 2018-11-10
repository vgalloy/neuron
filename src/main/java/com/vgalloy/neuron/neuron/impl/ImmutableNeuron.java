package com.vgalloy.neuron.neuron.impl;

import java.util.Arrays;

import com.vgalloy.neuron.neuron.AggregationFunction;

/**
 * Created by Vincent Galloy on 25/09/2018.
 *
 * @author Vincent Galloy
 */
public class ImmutableNeuron extends StandardNeuron {

    public ImmutableNeuron(final double firstCoefficient, final AggregationFunction aggregationFunction, final double... coefficients) {
        super(firstCoefficient, aggregationFunction, coefficients);
    }

    @Override
    protected ErrorOutput computeError(final double currentCoefficient, final double input, final double error) {
        final double errorPerInput = error * currentCoefficient;
        return new ErrorOutput(errorPerInput, 0);
    }

    @Override
    public String toString() {
        return "IN[" + firstCoefficient + Arrays.toString(coefficients) + "]";
    }
}
