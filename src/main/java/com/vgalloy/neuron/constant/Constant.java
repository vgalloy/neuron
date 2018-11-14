package com.vgalloy.neuron.constant;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Vincent Galloy on 22/04/17.
 *
 * @author Vincent Galloy
 */
public final class Constant {

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
        Arrays.setAll(coefficient, i -> Constant.doubleRandom());
        return coefficient;
    }

    public static boolean[] toBooleanArray(final List<Boolean> list) {
        final boolean[] result = new boolean[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
