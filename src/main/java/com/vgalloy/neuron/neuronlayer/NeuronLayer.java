package com.vgalloy.neuron.neuronlayer;

import java.util.List;
import java.util.stream.Collectors;

import com.vgalloy.neuron.constant.Constant;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public interface NeuronLayer {

    List<Boolean> compute(List<Boolean> input);

    /**
     * Train the current neuron layer.
     * @param input the list of boolean representing input
     * @param expectedResult the list of
     * @return the
     */
    List<Long> trainWithLong(List<Boolean> input, List<Long> expectedResult);

    default List<Long> trainWithBoolean(List<Boolean> input, List<Boolean> expectedResult) {
        List<Long> list = expectedResult.stream()
            .map(e -> e ? Constant.ONE : Constant.MINUS_ONE)
            .collect(Collectors.toList());
        return trainWithLong(input, list);
    }

    int size();
}
