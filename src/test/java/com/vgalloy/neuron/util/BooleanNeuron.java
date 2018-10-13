package com.vgalloy.neuron.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.vgalloy.neuron.constant.Constant;
import com.vgalloy.neuron.neuron.Neuron;
import com.vgalloy.neuron.neuron.Neurons;

import static com.vgalloy.neuron.constant.Constant.FALSE;
import static com.vgalloy.neuron.constant.Constant.TRUE;

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
            final List<Double> list = emptyList(size);
            list.set(a, 2 * TRUE);
            list.set(b, 2 * TRUE);
            return Neurons.of(-3 * TRUE, list);
        };
    }

    public static BiFunction<Integer, Integer, Neuron> or(final int size) {
        return (a, b) -> {
            final List<Double> list = emptyList(size);
            list.set(a, TRUE - FALSE);
            list.set(b, TRUE - FALSE);
            return Neurons.of(-FALSE, list);
        };
    }

    public static Function<Integer, Neuron> one(final int size) {
        return a -> {
            final List<Double> list = emptyList(size);
            list.set(a, TRUE);
            return Neurons.of(0d, list);
        };
    }

    public static BiFunction<Integer, Integer, Neuron> notAnd(final int size) {
        return (a, b) -> {
            final List<Double> list = emptyList(size);
            list.set(a, -2 * TRUE);
            list.set(b, -2 * TRUE);
            return Neurons.of(3 * TRUE, list);
        };
    }

    public interface IntVarArgsFunction<T> {
        T apply(Integer... values);
    }

    public static IntVarArgsFunction<Neuron> atLeast(final int size, final int minNumber) {
        return a -> {
            final int l = a.length;
            NeuronAssert.checkState(minNumber <= l, "Always false neuron");
            final List<Double> list = emptyList(size);
            final Double coeff = 2d;
            for (final int index : a) {
                list.set(index, coeff);
            }
            return Neurons.of(1  + coeff * (Constant.FALSE * (minNumber - l) - minNumber) , list);
        };
    }

    private static List<Double> emptyList(final int size) {
        final List<Double> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(0d);
        }
        return list;
    }
}
