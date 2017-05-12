package com.vgalloy.neuron.neuron;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Vincent Galloy on 12/05/2017.
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
    private NeuronInput(List<Boolean> input) {
        Objects.requireNonNull(input, "input can not be null");
        List<Boolean> fullInput = new ArrayList<>();
        fullInput.add(true);
        fullInput.addAll(input);
        this.fullInput = fullInput;
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
