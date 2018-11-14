package com.vgalloy.neuron.neuron;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.junit.Assert;

import com.vgalloy.neuron.neuron.builder.LengthBuilder;
import com.vgalloy.neuron.util.BiBooleanFunction;

/**
 * Created by Vincent Galloy on 24/09/2018.
 *
 * @author Vincent Galloy
 */
final class NeuronTestHelper {

    /**
     * Constructor.
     * Private to avoid instantiation
     */
    private NeuronTestHelper() {
        throw new AssertionError();
    }

    static void buildTest(final BiBooleanFunction biFunction, final LengthBuilder lengthBuilder) {
        Objects.requireNonNull(biFunction);
        for (int i = 0; i < 8; i++) {
            final Neuron neuron = lengthBuilder.withCoefficient(i % 8 == 0, i % 4 == 0, i % 2 == 0).build();
            train(neuron, biFunction);
            validate(neuron, biFunction);
        }
    }

    private static void train(final Neuron neuron, final BiBooleanFunction biFunction) {
        final List<boolean[]> training = Arrays.asList(
            new boolean[]{true, true},
            new boolean[]{true, false},
            new boolean[]{false, true},
            new boolean[]{false, false}
        );
        for (int i = 0; i < 50; i++) {
            Collections.shuffle(training);
            training.forEach(e -> neuron.train(biFunction.apply(e[0], e[1]), e));
        }
    }

    static void validate(final Neuron neuron, final BiBooleanFunction biFunction) {
        Assert.assertEquals(biFunction.apply(true, true), neuron.applyBoolean(true, true));
        Assert.assertEquals(biFunction.apply(false, true), neuron.applyBoolean(false, true));
        Assert.assertEquals(biFunction.apply(true, false), neuron.applyBoolean(true, false));
        Assert.assertEquals(biFunction.apply(false, false), neuron.applyBoolean(false, false));
    }
}
