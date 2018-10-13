package com.vgalloy.neuron.neuronlayer;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public interface NeuronLayer {

    boolean[] apply(boolean... input);

    /**
     * Train the current neuron layer.
     *
     * @param input the list of boolean representing input
     * @param error the error vector
     * @return the correction coefficients
     */
    double[] trainWithDouble(boolean[] input, double[] error);

    double[] trainWithBoolean(boolean[] input, boolean[] expectedSolution);

    /**
     * @return number of parameter in the input list.
     */
    int inputSize();

    /**
     * @return number of neuron in the layer
     */
    int neuronNumber();
}
