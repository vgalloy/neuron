package com.vgalloy.neuron.neuron.builder;

import com.vgalloy.neuron.constant.Constant;

/**
 * Created by Vincent Galloy on 19/10/18.
 *
 * @author Vincent Galloy
 */
public interface LengthBuilder {

    NeuronBuilder inputSize(int length);

    NeuronBuilder withCoefficient(double firstCoefficient, double... coefficients);

    default NeuronBuilder withCoefficient(boolean firstCoefficient, boolean... coefficients) {
        return this.withCoefficient(Constant.mapBoolean(firstCoefficient), Constant.mapBoolean(coefficients));
    }
}
