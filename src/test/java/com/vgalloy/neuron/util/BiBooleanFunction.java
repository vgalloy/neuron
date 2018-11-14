package com.vgalloy.neuron.util;

/**
 * Created by Vincent Galloy on 26/09/2018.
 *
 * @author Vincent Galloy
 */
public interface BiBooleanFunction {

    BiBooleanFunction OR = (a, b) -> a || b;
    BiBooleanFunction AND = (a, b) -> a && b;
    BiBooleanFunction FIRST = (a, b) -> a;
    BiBooleanFunction SECOND = (a, b) -> b;
    BiBooleanFunction NOT_AND = not(AND);
    BiBooleanFunction XOR = (a, b) -> a ^ b;

    static BiBooleanFunction not(final BiBooleanFunction function) {
        return (a, b) -> !function.apply(a, b);
    }

    boolean apply(boolean a, boolean b);
}
