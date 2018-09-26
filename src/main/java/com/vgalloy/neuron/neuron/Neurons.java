package com.vgalloy.neuron.neuron;

import java.util.Arrays;
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
        NeuronAssert.checkState(0 < size, "Can not create a neuron with no connection");

        List<Double> coefficient = IntStream.range(0, size)
            .boxed()
            .map(e -> Constant.doubleRandom())
            .collect(Collectors.toList());

        return of(coefficient);
    }

    public static Neuron of(List<Double> coefficients) {
        return of(Constant.doubleRandom(), coefficients);
    }

    public static Neuron of(Double firstCoefficient, List<Double> coefficients) {
        return new SimpleNeuron(firstCoefficient, coefficients);
    }

    public static Neuron of(Double firstCoefficient, Double... coefficients) {
        return of(firstCoefficient, Arrays.asList(coefficients));
    }
}
