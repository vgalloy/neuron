package com.vgalloy.neuron.neuron;

import java.util.List;
import java.util.function.Function;

import com.vgalloy.neuron.constant.Constant;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public interface Neuron {

    boolean apply(final List<Boolean> input);

    List<Long> train(List<Boolean> input, boolean expected);
}
