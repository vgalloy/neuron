package com.vgalloy.neuron.neuron.builder;

import com.vgalloy.neuron.neuron.AggregationFunction;

/**
 * Created by Vincent Galloy on 19/10/18.
 *
 * @author Vincent Galloy
 */
public interface LengthBuilder {

    NeuronBuilder inputSize(int length);

    NeuronBuilder withCoefficient(double firstCoefficient, double... coefficients);

    NeuronBuilder withCoefficient(boolean firstCoefficient, boolean... coefficients);

    AggregationFunction getFunction();
}
