package com.vgalloy.neuron.neuronlayer;

import java.util.Arrays;

import com.vgalloy.neuron.neuron.AggregationFunction;
import com.vgalloy.neuron.neuron.Neuron;
import com.vgalloy.neuron.util.NeuronAssert;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
class NeuronLayerImpl implements NeuronLayer {

    private final Neuron[] neurons;

    NeuronLayerImpl(final Neuron[] neurons) {
        NeuronAssert.state(neurons.length != 0, "Layer must contains at least one neuron");
        this.neurons = neurons;
    }

    @Override
    public double[] apply(final double... input) {
        final double[] result = new double[neurons.length];
        for (int i = 0; i < neurons.length; i++) {
            result[i] = neurons[i].apply(input);
        }
        return result;
    }

    @Override
    public boolean[] apply(final boolean... input) {
        return function().activation(apply(function().toDoubleArray(input)));
    }

    @Override
    public double[] train(final double[] input, final double[] error) {
        NeuronAssert.state(neurons.length == error.length, "Error vector size must be equals to neuron layer size.");

        final double[][] coefficientCorrections = new double[neuronNumber()][];
        for (int i = 0; i < neurons.length; i++) {
            final Neuron neuron = neurons[i];
            final double[] correction = neuron.train(error[i], input);
            NeuronAssert.state(correction.length == inputSize(), "Correction list size should be equals to input list size.");
            coefficientCorrections[i] = correction;
        }

        final double[] result = new double[inputSize()];
        for (double[] coefficientCorrection : coefficientCorrections) {
            for (int i = 0; i < inputSize(); i++) {
                result[i] = result[i] + coefficientCorrection[i] / neuronNumber();
            }
        }
        return result;
    }

    @Override
    public double[] train(boolean[] input, boolean[] expectedSolution) {
        final boolean[] result = apply(input);
        final double[] diff = new double[expectedSolution.length];
        for (int i = 0; i < expectedSolution.length; i++) {
            diff[i] = function().toDouble(expectedSolution[i]) - function().toDouble(result[i]);
        }
        return train(function().toDoubleArray(input), diff);
    }

    @Override
    public int inputSize() {
        return neurons[0].inputSize();
    }

    @Override
    public int neuronNumber() {
        return neurons.length;
    }

    @Override
    public AggregationFunction function() {
        return neurons[0].function();
    }

    @Override
    public String toString() {
        return "Layer" + Arrays.toString(neurons);
    }
}
