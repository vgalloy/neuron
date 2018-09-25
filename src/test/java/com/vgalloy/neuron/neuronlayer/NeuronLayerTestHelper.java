package com.vgalloy.neuron.neuronlayer;

import org.junit.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

import com.vgalloy.neuron.neuronsystem.NeuronSystem;
import com.vgalloy.neuron.neuronsystem.NeuronSystemBuilder;
import com.vgalloy.neuron.util.NeuronAssert;

/**
 * Created by Vincent Galloy on 24/09/2018.
 *
 * @author Vincent Galloy
 */
final class NeuronLayerTestHelper {

    /**
     * Constructor.
     * Private to avoid instantiation
     */
    private NeuronLayerTestHelper() {
        throw new AssertionError();
    }

    static void test(BiFunction<Integer, Integer, Integer> biFunction) {
        final NeuronLayer neuronLayer = NeuronLayers.of(4, 3);

        train(neuronLayer, biFunction);
        validate(neuronLayer, biFunction);
    }

    private static void validate(NeuronLayer neuronLayer, BiFunction<Integer, Integer, Integer> biFunction) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                final List<Boolean> result = neuronLayer.apply(toArgs(i, j));
                final Integer value = toInt(result);
                Assert.assertEquals(biFunction.apply(i, j), value);
            }
        }
    }

    private static void train(NeuronLayer neuronLayer, BiFunction<Integer, Integer, Integer> biFunction) {
        List<List<Integer>> trainings = buildTrainings();
        for (int i = 0; i < 500; i++) {
            Collections.shuffle(trainings);
            for (List<Integer> input : trainings) {
                final Integer first = input.get(0);
                final Integer second = input.get(1);
                final List<Boolean> args = toArgs(first, second);
                final List<Boolean> result = toBoolean(biFunction.apply(first, second), 3);
                neuronLayer.trainWithBoolean(args, result);
                System.out.println(neuronLayer);
            }
        }
    }

    private static List<List<Integer>> buildTrainings() {
        final List<List<Integer>> trainings = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                trainings.add(Arrays.asList(i, j));
            }
        }
        return trainings;
    }

    static List<Boolean> toBoolean(final int i, int size) {
        NeuronAssert.checkState(i < Math.pow(2, size), "Number " + i + " is higher than 2^" + size);
        final List<Boolean> result = new ArrayList<>();
        for(int j = 0; j < size; j++) {
            result.add(i / (int) Math.pow(2, j) % 2 != 0);
        }
        return result;
    }

    static List<Boolean> toArgs(final int a, final int b) {
        final List<Boolean> args = toBoolean(a, 2);
        args.addAll(toBoolean(b, 2));
        return args;
    }

    static int toInt(final List<Boolean> booleans) {
        Objects.requireNonNull(booleans);

        int result = 0;
        int factor = 0;
        for (final boolean coefficient : booleans) {
            if (coefficient) {
                result += Math.pow(2, factor);
            }
            factor++;
        }

        return result;
    }

}
