package com.vgalloy.neuron.neuron;

import java.util.List;
import java.util.function.Function;

import com.vgalloy.neuron.constant.Constant;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public interface Neuron extends Function<List<Boolean>, Boolean> {

    default List<Long> train(List<Boolean> input, Boolean expected) {
        Long expectedAsLong = Constant.map(expected);
        return train(input, expectedAsLong);
    }

    List<Long> train(List<Boolean> input, Long expected);
}
