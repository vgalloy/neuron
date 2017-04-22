package com.vgalloy.neuron.neuronsystem;

import java.util.List;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public interface NeuronSystem {

    List<Boolean> compute(List<Boolean> input);

    void train(List<Boolean> input, List<Boolean> expectedSolution);
}
