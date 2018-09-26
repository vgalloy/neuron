package com.vgalloy.neuron.neuron;

import java.util.List;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public final class TanHNeuron extends AbstractNeuron {

    public TanHNeuron(final double firstCoefficient, final List<Double> coefficients) {
        super(firstCoefficient, coefficients, AggregationFunction.TAN_H);
    }

    @Override
    public String toString() {
        return "TanH" + getCoefficients();
    }
}
