package com.vgalloy.neuron.util;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public interface IntBiFunction {

    IntBiFunction ADD = (a, b) -> a + b;
    IntBiFunction ADD_ONE = (a, b) -> a + 1;

    int apply(int a, int b);
}
