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
     * @param input the list of boolean representing input
     * @param expectedResult the list of
     * @return the
     */
    List<Long> trainWithLong(List<Boolean> input, List<Long> expectedResult);

    default List<Long> trainWithBoolean(List<Boolean> input, List<Boolean> expectedResult) {
        final List<Long> list = Constant.map(expectedResult);
        return trainWithLong(input, list);
    }

    int size();
}
