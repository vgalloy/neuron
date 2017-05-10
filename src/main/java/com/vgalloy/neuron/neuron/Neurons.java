package com.vgalloy.neuron.neuron;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.vgalloy.neuron.constant.Constant;
import com.vgalloy.neuron.util.NeuronAssert;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public final class Neurons {

    private static final Random RANDOM = new SecureRandom();

    private Neurons() {
        throw new AssertionError();
    }

    public static Neuron of(int size) {
        NeuronAssert.checkState(size <= 0, "Can not create a neuron with no connection");

        List<Long> coefficient = IntStream.range(0, size)
            .boxed()
            .map(e -> RANDOM.nextInt() > 0 ? Constant.ONE : Constant.MINUS_ONE)
            .collect(Collectors.toList());

        return of(coefficient);
    }

    public static Neuron of(List<Long> coefficient) {
        return new SimpleNeuron(Constant.MINUS_ONE, coefficient);
    }
}
