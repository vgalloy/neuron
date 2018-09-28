package com.vgalloy.neuron.util;

import java.util.function.BiFunction;

/**
 * Created by Vincent Galloy on 26/09/2018.
 *
 * @author Vincent Galloy
 */
public final class BooleanFunction {

    public static final BiFunction<Boolean, Boolean, Boolean> OR = (a, b) -> a || b;
    public static final BiFunction<Boolean, Boolean, Boolean> AND = (a, b) -> a && b;
    public static final BiFunction<Boolean, Boolean, Boolean> FIRST = (a, b) -> a;
    public static final BiFunction<Boolean, Boolean, Boolean> SECOND = (a, b) -> b;
    public static final BiFunction<Boolean, Boolean, Boolean> NOT_AND = not(AND);
    public static final BiFunction<Boolean, Boolean, Boolean> XOR = (a, b) -> a ^ b;

    public static BiFunction<Boolean, Boolean, Boolean> not(final BiFunction<Boolean, Boolean, Boolean> function) {
        return (a, b) -> !function.apply(a, b);
    }

    /**
     * Constructor.
     * Private to avoid instantiation
     */
    private BooleanFunction() {
        throw new AssertionError();
    }
}
