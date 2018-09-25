package com.vgalloy.neuron.constant;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Vincent Galloy on 22/04/17.
 *
 * @author Vincent Galloy
 */
public final class Constant {

    public static final double TRUE = 1;
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
        return mapBoolean(random());
    }

    public static double mapBoolean(final boolean value) {
        if (value) {
            return 1d;
        }
        return 0L;
    }

    public static List<Double> mapBoolean(final List<Boolean> list) {
        return list.stream()
            .map(Constant::mapBoolean)
            .collect(Collectors.toList());
    }
}
