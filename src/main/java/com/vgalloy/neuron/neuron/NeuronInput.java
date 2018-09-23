package com.vgalloy.neuron.neuron;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Vincent Galloy on 12/05/2017.
 * This class help for input management. Input get special behaviour for the first coefficient. It should be equals to ONE in any case.
 *
 * @author Vincent Galloy
 */
final class NeuronInput {

    private final List<Boolean> fullInput;

    /**
     * Constructor.
     * To prevent external instantiation
     *
     * @param input the input
     */
    private NeuronInput(final List<Boolean> input) {
        Objects.requireNonNull(input, "Input can not be null");
        this.fullInput = new ArrayList<>();
        fullInput.add(true);
        fullInput.addAll(input);
    }

    /**
     * Static factory
     *
     * @param input the input
     * @return the NeuronInput class
     */
    static NeuronInput of(List<Boolean> input) {
        return new NeuronInput(input);
    }

    boolean get(int i) {
        return fullInput.get(i);
    }

    int size() {
        return fullInput.size();
    }
}
