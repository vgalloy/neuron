package com.vgalloy.neuron.neuron;

import com.vgalloy.neuron.neuron.builder.Builder;
import com.vgalloy.neuron.neuron.builder.LengthBuilder;
import com.vgalloy.neuron.neuron.builder.TypeBuilder;

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

    public static TypeBuilder builder() {
        return Builder.builder();
    }

    public static LengthBuilder tanh() {
        return builder().withType(AggregationFunction.TAN_H);
    }

    public static LengthBuilder linear() {
        return builder().withType(AggregationFunction.IDENTITY);
    }
}
