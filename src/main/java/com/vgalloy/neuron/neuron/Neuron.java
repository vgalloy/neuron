package com.vgalloy.neuron.neuron;

import java.util.List;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public interface Neuron {

    boolean apply(List<Boolean> input);

    List<Double> train(List<Boolean> input, boolean expected);

    List<Double> train(List<Boolean> input, double diff);

    int inputSize();
}
