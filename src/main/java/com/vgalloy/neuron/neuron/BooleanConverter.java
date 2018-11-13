package com.vgalloy.neuron.neuron;

import java.util.Arrays;

/**
 * Created by Vincent Galloy on 13/11/18.
 *
 * @author Vincent Galloy
 */
public interface BooleanConverter {

    double trueValue();

    double falseValue();

    default double toDouble(final boolean value) {
        if (value) {
            return trueValue();
        }
        return falseValue();
    }

    default double[] toDoubleArray(final boolean... list) {
        final double[] result = new double[list.length];
        Arrays.setAll(result, i -> toDouble(list[i]));
        return result;
    }
}
