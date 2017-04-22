package com.vgalloy.neuron.constant;

/**
 * Created by Vincent Galloy on 22/04/17.
 *
 * @author Vincent Galloy
 */
public final class Constant {

    public static final long GLOBAL_MULTIPLICATOR = 1_000;

    public static final long ONE = GLOBAL_MULTIPLICATOR;
    public static final long MINUS_ONE = -1 * GLOBAL_MULTIPLICATOR;

    /**
     * Constructor.
     * Constant class can not be instantiate
     */
    private Constant() {
        throw new AssertionError();
    }
}
