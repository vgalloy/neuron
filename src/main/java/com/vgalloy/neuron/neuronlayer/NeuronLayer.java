package com.vgalloy.neuron.neuronlayer;

import java.util.List;
import java.util.function.Function;

import com.vgalloy.neuron.constant.Constant;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public interface NeuronLayer extends Function<List<Boolean>, List<Boolean>> {

    /**
     * Train the current neuron layer.
     *
     * @param input the list of boolean representing input
     * @param error  the error vector
     * @return the correction coefficients
     */
    List<Double> trainWithDouble(List<Boolean> input, List<Double> error);

    List<Double> trainWithBoolean(List<Boolean> input, List<Boolean> expectedSolution);

    /**
     * @return number of parameter in the input list.
     */
    int inputSize();

    /**
     * @return number of neuron in the layer
     */
    int neuronNumber();
}
