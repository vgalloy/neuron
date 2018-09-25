package com.vgalloy.neuron.neuronsystem;

import org.junit.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

import com.vgalloy.neuron.util.NeuronAssert;

/**
 * Created by Vincent Galloy on 24/09/2018.
 *
 * @author Vincent Galloy
 */
final class NeuronSystemTestHelper {

    /**
     * Constructor.
     * Private to avoid instantiation
     */
    private NeuronSystemTestHelper() {
        throw new AssertionError();
    }

    static void test(BiFunction<Integer, Integer, Integer> biFunction) {
        final NeuronSystem neuronSystem = new NeuronSystemBuilder(4, 4)
            .addLayer(3)
            .addLayer(3)
            .addLayer(3)
            .build();

        train(neuronSystem, biFunction);
        validate(neuronSystem, biFunction);
    }

    private static void validate(NeuronSystem neuronSystem, BiFunction<Integer, Integer, Integer> biFunction) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                final List<Boolean> result = neuronSystem.apply(toArgs(i, j));
                final Integer value = toInt(result);
                Assert.assertEquals(biFunction.apply(i, j), value);
            }
        }
    }

    private static void train(NeuronSystem neuronSystem, BiFunction<Integer, Integer, Integer> biFunction) {
        List<List<Integer>> trainings = buildTrainings();
        for (int i = 0; i < 500; i++) {
            Collections.shuffle(trainings);
            for (List<Integer> input : trainings) {
                final Integer first = input.get(0);
                final Integer second = input.get(1);
                final List<Boolean> args = toArgs(first, second);
                final List<Boolean> result = toBoolean(biFunction.apply(first, second), 3);
                neuronSystem.trainWithBoolean(args, result);
//                System.out.println(neuronSystem);
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
        for (int j = 0; j < size; j++) {
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
