package com.vgalloy.neuron.neuronsystem;

import java.util.List;
import java.util.function.Function;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public interface NeuronSystem extends Function<List<Boolean>, List<Boolean>> {

    /**
     *
     * @return the if the system correctly response to question. System should not change.
     */
    boolean trainWithBoolean(List<Boolean> input, List<Boolean> expectedSolution);

    int inputSize();

    int outputSize();
}
