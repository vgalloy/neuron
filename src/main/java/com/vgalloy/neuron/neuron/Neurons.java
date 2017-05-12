package com.vgalloy.neuron.neuron;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        NeuronAssert.checkState(size <= 0, "Can not create a neuron with no connection");

        List<Long> coefficient = IntStream.range(0, size)
            .boxed()
            .map(e -> Constant.random())
            .collect(Collectors.toList());

        return of(coefficient);
    }

    public static Neuron of(List<Long> coefficients) {
        return of(Constant.random(), coefficients);
    }

    public static Neuron of(Long firstCoefficient, List<Long> coefficients) {
        return new SimpleNeuron(firstCoefficient, coefficients);
    }
}
