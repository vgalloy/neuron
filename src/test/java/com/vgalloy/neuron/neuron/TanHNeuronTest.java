package com.vgalloy.neuron.neuron;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
@Ignore
public final class TanHNeuronTest {

    @Test
    public void and() {
        buildTest((a, b) -> a && b);
    }

    @Test
    public void or() {
        buildTest((a, b) -> a || b);
    }

    @Test
    public void first() {
        buildTest((a, b) -> a);
    }

    @Test
    public void notSecond() {
        buildTest((a, b) -> !b);
    }

    private static void buildTest(final BiFunction<Boolean, Boolean, Boolean> biFunction) {
        Objects.requireNonNull(biFunction);
        for (int i = 0; i < 4; i++) {
            final List<Double> coefficients = Arrays.asList(2d, 3d);
            final Neuron neuron = new TanHNeuron(coefficients);
            NeuronTestHelper.train(neuron, biFunction);
            NeuronTestHelper.validate(neuron, biFunction);
        }
    }
}
