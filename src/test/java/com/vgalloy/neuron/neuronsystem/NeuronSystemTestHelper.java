package com.vgalloy.neuron.neuronsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

import org.junit.Assert;

import com.vgalloy.neuron.util.NeuronAssert;

/**
 * Created by Vincent Galloy on 24/09/2018.
 *
 * @author Vincent Galloy
 */
final class NeuronSystemTestHelper {

    private static final int DEFAULT_TRAINING_NUMBER = 5_000;

    /**
     * Constructor.
     * Private to avoid instantiation
     */
    private NeuronSystemTestHelper() {
        throw new AssertionError();
    }

    static void test(BiFunction<Integer, Integer, Integer> biFunction) {
        final NeuronSystem neuronSystem = new NeuronSystemBuilder(4, 6)
            .addLayer(4)
            .addLayer(4)
            .addLayer(3)
            .build();

        train(neuronSystem, biFunction, 2);
        validate(neuronSystem, biFunction, 2);
    }

    static void validate(final NeuronSystem neuronSystem, final BiFunction<Integer, Integer, Integer> biFunction, final int size) {
        for (int i = 0; i < Math.pow(2, size); i++) {
            for (int j = 0; j < Math.pow(2, size); j++) {
                final List<Boolean> result = neuronSystem.apply(toArgs(size, i, j));
                final Integer value = toInt(result);
                Assert.assertEquals(biFunction.apply(i, j), value);
            }
        }
    }

    static void train(final NeuronSystem neuronSystem, final BiFunction<Integer, Integer, Integer> biFunction, final int size) {
        train(neuronSystem, biFunction, size, DEFAULT_TRAINING_NUMBER);
    }

    static void train(final NeuronSystem neuronSystem, final BiFunction<Integer, Integer, Integer> biFunction, final int size, final int training) {
        List<List<Integer>> trainings = buildTrainings(size);
        for (int i = 0; i < training; i++) {
            Collections.shuffle(trainings);
            for (List<Integer> input : trainings) {
                final Integer first = input.get(0);
                final Integer second = input.get(1);
                final List<Boolean> args = toArgs(size, first, second);
                final List<Boolean> result = toBoolean(biFunction.apply(first, second), size + 1);
                neuronSystem.trainWithBoolean(args, result);
            }
        }
    }

    private static List<List<Integer>> buildTrainings(final int size) {
        final List<List<Integer>> trainings = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, size); i++) {
            for (int j = 0; j < Math.pow(2, size); j++) {
                trainings.add(Arrays.asList(i, j));
            }
        }
        return trainings;
    }

    static List<Boolean> toBoolean(final int i, int size) {
        NeuronAssert.checkState(i < Math.pow(2, size), "Number " + i + " is higher than 2^" + size);
        final List<Boolean> result = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            result.add(i / (int) Math.pow(2, j) % 2 != 0);
        }
        return result;
    }

    private static List<Boolean> toArgs(final int size, final int a, final int b) {
        final List<Boolean> args = toBoolean(a, size);
        args.addAll(toBoolean(b, size));
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
