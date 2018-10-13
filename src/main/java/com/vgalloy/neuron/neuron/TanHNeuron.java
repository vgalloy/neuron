package com.vgalloy.neuron.neuron;

import java.util.Arrays;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public final class TanHNeuron extends AbstractNeuron {

    public TanHNeuron(final double firstCoefficient, final double... coefficients) {
        super(firstCoefficient, AggregationFunction.TAN_H, coefficients);
    }

    @Override
    public String toString() {
        return "TanH" + Arrays.toString(getCoefficients());
    }
}
