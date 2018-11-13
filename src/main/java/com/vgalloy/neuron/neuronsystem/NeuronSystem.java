package com.vgalloy.neuron.neuronsystem;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public interface NeuronSystem {

    boolean[] apply(boolean... booleans);

    void trainWithBoolean(boolean[] input, boolean... expectedSolution);

    int inputSize();

    int outputSize();
}
