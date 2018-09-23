package com.vgalloy.neuron.util;

import java.util.Objects;

/**
 * Created by Vincent Galloy on 10/05/17.
 *
 * @author Vincent Galloy
 */
public final class NeuronAssert {

    /**
     * Constructor.
     * To prevent instantiation
     */
    private NeuronAssert() {
        throw new IllegalAccessError();
    }

    /**
     * Helper to throws exception
     *
     * @param condition the condition
     * @param message   the message
     */
    public static void checkState(boolean condition, String message) {
        Objects.requireNonNull(message, "message");
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }
}
