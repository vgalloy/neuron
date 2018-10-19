package com.vgalloy.neuron.neuron.builder;

/**
 * Created by Vincent Galloy on 19/10/18.
 *
 * @author Vincent Galloy
 */
public interface LengthBuilder {

    NeuronBuilder withLength(int length);

    NeuronBuilder withCoefficient(double firstCoefficient, double... coefficients);
}
