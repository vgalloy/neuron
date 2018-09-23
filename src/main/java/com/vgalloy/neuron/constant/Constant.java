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

    public static final long GLOBAL_MULTIPLICATOR = 1_000;
    public static final long ONE = GLOBAL_MULTIPLICATOR;
    public static final long MINUS_ONE = -1 * GLOBAL_MULTIPLICATOR;

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

    public static long longRandom() {
        return map(random());
    }

    public static long map(boolean value) {
        if (value) {
            return Constant.ONE;
        }
        return 0L;
    }

    public static List<Long> map(List<Boolean> list) {
        return list.stream()
                .map(Constant::map)
                .collect(Collectors.toList());
    }
}
