package com.vgalloy.neuron.neuron;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public interface Neuron {

    double apply(boolean... input);

    double apply(double... input);

    boolean applyBoolean(boolean... input);

    double[] train(boolean expected, boolean... input);

    double[] train(double diff, boolean... input);

    int inputSize();
}
