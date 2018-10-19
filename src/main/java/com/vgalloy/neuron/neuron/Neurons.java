package com.vgalloy.neuron.neuron;

import com.vgalloy.neuron.neuron.builder.Builder;
import com.vgalloy.neuron.neuron.builder.LengthBuilder;
import com.vgalloy.neuron.neuron.builder.TypeBuilder;
import com.vgalloy.neuron.util.NeuronAssert;

/**
 * Created by Vincent Galloy on 01/04/17.
 * This class manage {@link Neuron} creation.
 *
 * @author Vincent Galloy
 */
public final class Neurons {

    private Neurons() {
        throw new AssertionError();
    }

    public static Neuron of(int size) {
        NeuronAssert.checkState(0 < size, "Can not create a neuron with no connection");

        return tanh().withLength(size).build();
    }

    public static Neuron of(final double firstCoefficient, final double... coefficients) {
        return tanh().withCoefficient(firstCoefficient, coefficients).build();
    }

    public static LengthBuilder tanh() {
        return builder().withType(AggregationFunction.TAN_H);
    }

    public static LengthBuilder linear() {
        return builder().withType(AggregationFunction.IDENTITY);
    }

    public static TypeBuilder builder() {
        return Builder.builder();
    }
}
