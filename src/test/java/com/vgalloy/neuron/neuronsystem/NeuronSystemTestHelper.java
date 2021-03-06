package com.vgalloy.neuron.neuronsystem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.junit.Assert;

import com.vgalloy.neuron.util.IntBiFunction;
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

    static void validate(final NeuronSystem neuronSystem, final IntBiFunction biFunction, final int size) {
        for (int i = 0; i < Math.pow(2, size); i++) {
            for (int j = 0; j < Math.pow(2, size); j++) {
                final boolean[] result = neuronSystem.apply(toArgs(size, i, j));
                final int value = toInt(result);
                Assert.assertEquals(biFunction.apply(i, j), value);
            }
        }
    }

    static void train(final NeuronSystem neuronSystem, final IntBiFunction biFunction, final int size) {
        train(neuronSystem, biFunction, size, DEFAULT_TRAINING_NUMBER);
    }

    static void train(final NeuronSystem neuronSystem, final IntBiFunction biFunction, final int size, final int training) {
        List<int[]> trainings = buildTrainings(size);
        for (int i = 0; i < training; i++) {
            Collections.shuffle(trainings);
            for (int[] input : trainings) {
                final int first = input[0];
                final int second = input[1];
                final boolean[] args = toArgs(size, first, second);
                final boolean[] result = toBoolean(biFunction.apply(first, second), size + 1);
                neuronSystem.train(args, result);
//                System.out.println(neuronSystem);
            }
        }
    }

    private static List<int[]> buildTrainings(final int size) {
        final List<int[]> trainings = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, size); i++) {
            for (int j = 0; j < Math.pow(2, size); j++) {
                trainings.add(new int[] {i, j});
            }
        }
        return trainings;
    }

    static boolean[] toBoolean(final int i, int size) {
        NeuronAssert.state(i < Math.pow(2, size), "Number " + i + " is higher than 2^" + size);
        final boolean[] result = new boolean[size];
        for (int j = 0; j < size; j++) {
            result[j] = (i / (int) Math.pow(2, j) % 2) != 0;
        }
        return result;
    }

    private static boolean[] toArgs(final int size, final int a, final int b) {
        final boolean[] args = toBoolean(a, size);
        return concat(args, toBoolean(b, size));
    }

    private static boolean[] concat(boolean[] a, boolean[] b) {
        int aLen = a.length;
        int bLen = b.length;

        boolean[] c = (boolean[]) Array.newInstance(boolean.class, aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }

    static int toInt(final boolean... booleans) {
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
