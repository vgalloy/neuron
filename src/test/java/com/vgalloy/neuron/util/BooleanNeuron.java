package com.vgalloy.neuron.util;

import com.vgalloy.neuron.neuron.Neuron;
import com.vgalloy.neuron.neuron.Neurons;

/**
 * Created by Vincent Galloy on 26/09/2018.
 *
 * @author Vincent Galloy
 */
public final class BooleanNeuron {

    public static final Neuron OR = Neurons.of(1d, 2d, 2d);
    public static final Neuron AND = Neurons.of(-3d, 2d, 2d);
    public static final Neuron FIRST = Neurons.of(0d, 1d, 0d);
    public static final Neuron SECOND = Neurons.of(0d, 0d, 1d);
    public static final Neuron NOT_AND = Neurons.of(2d, -3d, -3d);

    /**
     * Constructor.
     * Private to avoid instantiation
     */
    private BooleanNeuron() {
        throw new AssertionError();
    }
}
