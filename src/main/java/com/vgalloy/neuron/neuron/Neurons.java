package com.vgalloy.neuron.neuron;

import com.vgalloy.neuron.constant.Constant;
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

        final double[] coefficient = new double[size];
        for (int i = 0; i < size; i++) {
            coefficient[i] = Constant.doubleRandom();
        }
        return of(Constant.doubleRandom(), coefficient);
    }

    public static Neuron of(final double firstCoefficient, final double... coefficients) {
        return new TanHNeuron(firstCoefficient, coefficients);
    }
}
