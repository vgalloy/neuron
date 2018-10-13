package com.vgalloy.neuron.neuron;

import java.util.Objects;

/**
 * Created by Vincent Galloy on 12/05/2017.
 * This class help for input management. Input get special behaviour for the first coefficient. It should be equals to ONE in any case.
 *
 * @author Vincent Galloy
 */
final class NeuronInput {

    private final boolean[] fullInput;

    /**
     * Constructor.
     * To prevent external instantiation
     *
     * @param input the input
     */
    private NeuronInput(final boolean[] input) {
        Objects.requireNonNull(input, "Input can not be null");
        int newLength = input.length + 1;
        fullInput = new boolean[newLength];
        fullInput[0] = true;
        System.arraycopy(input, 0, fullInput, 1, input.length);
    }

    /**
     * Static factory
     *
     * @param input the input
     * @return the NeuronInput class
     */
    static NeuronInput of(final boolean[] input) {
        return new NeuronInput(input);
    }

    boolean get(int i) {
        return fullInput[i];
    }

    int size() {
        return fullInput.length;
    }
}
