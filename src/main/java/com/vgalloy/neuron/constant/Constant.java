package com.vgalloy.neuron.constant;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * Created by Vincent Galloy on 22/04/17.
 *
 * @author Vincent Galloy
 */
public final class Constant {

    public static final double TRUE = 1d;
    public static final double FALSE = -1d;

    private static final Random RANDOM = new SecureRandom();

    /**
     * Constructor.
     * Constant class can not be instantiate
     */
    private Constant() {
        throw new IllegalAccessError();
    }

    public static boolean random() {
        return RANDOM.nextBoolean();
    }

    public static double doubleRandom() {
        return 2 * RANDOM.nextDouble() - 1;
    }

    public static double[] doubleRandom(int length) {
        final double[] coefficient = new double[length];
        for (int i = 0; i < length; i++) {
            coefficient[i] = Constant.doubleRandom();
        }
        return coefficient;
    }

    public static double toDoubleList(final boolean value) {
        if (value) {
            return TRUE;
        }
        return FALSE;
    }

    public static double[] toDoubleArray(final boolean... list) {
        final double[] result = new double[list.length];
        for (int i = 0; i < list.length; i++) {
            result[i] = toDoubleList(list[i]);
        }
        return result;
    }

    public static List<Double> toDoubleList(final List<Boolean> list) {
        return list.stream()
            .map(Constant::toDoubleList)
            .collect(Collectors.toList());
    }

    public static double[] toDoubleArray(final List<Double> list) {
        final double[] result = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static boolean[] toBooleanArray(final List<Boolean> list) {
        final boolean[] result = new boolean[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static List<Double> toDoubleList(final double[] train) {
        return DoubleStream.of(train)
            .boxed()
            .collect(Collectors.toList());
    }

    public static List<Boolean> toBooleanList(final boolean[] train) {
        final List<Boolean> list = new ArrayList<>();
        for (final boolean b : train) {
            list.add(b);
        }
        return list;
    }
}
