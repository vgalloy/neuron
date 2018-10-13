package com.vgalloy.neuron.util;

import java.util.function.BiFunction;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public class IntFunction {

    public static final BiFunction<Integer, Integer, Integer> ADD = (a, b) -> a + b;
    public static final BiFunction<Integer, Integer, Integer> ADD_ONE = (a, b) -> a + 1;

    /**
     * Constructor.
     * Private to avoid instantiation
     */
    private IntFunction() {
        throw new AssertionError();
    }
}
