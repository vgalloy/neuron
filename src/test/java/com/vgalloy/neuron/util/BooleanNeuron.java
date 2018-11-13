package com.vgalloy.neuron.util;

import java.util.function.BiFunction;
import java.util.function.Function;

import com.vgalloy.neuron.neuron.AggregationFunction;
import com.vgalloy.neuron.neuron.Neuron;
import com.vgalloy.neuron.neuron.Neurons;

/**
 * Created by Vincent Galloy on 26/09/2018.
 *
 * @author Vincent Galloy
 */
public final class BooleanNeuron {

    public static final Neuron OR = or(2).apply(0, 1);
    public static final Neuron AND = and(2).apply(0, 1);
    public static final Neuron FIRST = one(2).apply(0);
    public static final Neuron SECOND = one(2).apply(1);
    public static final Neuron NOT_AND = notAnd(2).apply(0, 1);

    /**
     * Constructor.
     * Private to avoid instantiation
     */
    private BooleanNeuron() {
        throw new AssertionError();
    }

    public static BiFunction<Integer, Integer, Neuron> and(final int size) {
        return (a, b) -> {
            final double[] list = new double[size];
            final AggregationFunction tanh = Neurons.tanh().getFunction();
            list[a] = 2 * tanh.trueValue();
            list[b] = 2 * tanh.trueValue();
            return Neurons.tanh()
                .withCoefficient(-3 * tanh.trueValue(), list)
                .build(true);
        };
    }

    public static BiFunction<Integer, Integer, Neuron> or(final int size) {
        return (a, b) -> {
            final double[] list = new double[size];
            final AggregationFunction tanh = Neurons.tanh().getFunction();
            list[a] = tanh.trueValue() - tanh.falseValue();
            list[b] = tanh.trueValue() - tanh.falseValue();
            return Neurons.tanh()
                .withCoefficient(-tanh.falseValue(), list)
                .build(true);
        };
    }

    public static Function<Integer, Neuron> one(final int size) {
        return a -> {
            final double[] list = new double[size];
            final AggregationFunction tanh = Neurons.tanh().getFunction();
            list[a] = tanh.trueValue();
            return Neurons.tanh()
                .withCoefficient(0d, list)
                .build(true);
        };
    }

    public static BiFunction<Integer, Integer, Neuron> notAnd(final int size) {
        return (a, b) -> {
            final double[] list = new double[size];
            final AggregationFunction tanh = Neurons.tanh().getFunction();
            list[a] = -2 * tanh.trueValue();
            list[b] = -2 * tanh.trueValue();
            return Neurons.tanh()
                .withCoefficient(3 * tanh.trueValue(), list)
                .build(true);
        };
    }

    public interface IntVarArgsFunction<T> {
        T apply(int... values);
    }

    public static IntVarArgsFunction<Neuron> atLeast(final int size, final int minNumber) {
        return a -> {
            final AggregationFunction tanh = Neurons.tanh().getFunction();
            final int l = a.length;
            NeuronAssert.state(minNumber <= l, "Always false neuron");
            final double[] list = new double[size];
            final double coeff = 2d;
            for (final int index : a) {
                list[index] = coeff;
            }
            return Neurons.tanh()
                .withCoefficient(1 + coeff * (tanh.falseValue() * (minNumber - l) - minNumber), list)
                .build(true);
        };
    }
}
