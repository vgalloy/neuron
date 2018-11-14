package com.vgalloy.neuron.neuronsystem;

import java.util.Arrays;
import java.util.Objects;

import com.vgalloy.neuron.neuron.AggregationFunction;
import com.vgalloy.neuron.neuronlayer.NeuronLayer;
import com.vgalloy.neuron.util.NeuronAssert;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
final class NeuronSystemImpl implements NeuronSystem {

    private final NeuronLayer[] neuronLayers;

    NeuronSystemImpl(final NeuronLayer... neuronLayers) {
        this.neuronLayers = Objects.requireNonNull(neuronLayers);
    }

    @Override
    public double[] apply(final double... input) {
        double[] list = input;
        for (final NeuronLayer neuronLayer : neuronLayers) {
            list = neuronLayer.apply(list);
        }
        return list;
    }

    @Override
    public boolean[] apply(final boolean... inputs) {
        return function().activation(apply(function().toDoubleArray(inputs)));
    }

    @Override
    public void train(final boolean[] input, final boolean... expectedSolution) {
        NeuronAssert.state(outputSize() == expectedSolution.length, "Expected solutions size is : " + expectedSolution.length + " must be " + input.length);

        train(function().toDoubleArray(input), function().toDoubleArray(expectedSolution));
    }

    @Override
    public void train(final double[] input, final double... expectedSolution) {
        NeuronAssert.state(outputSize() == expectedSolution.length, "Expected solutions size is : " + expectedSolution.length + " must be " + input.length);
        final double[][] middleResult = computeMiddleResult(input);
        double[] target = expectedSolution.clone();

        // back prop
        for (int i = 0; i < neuronLayers.length; i++) {
            final int index = neuronLayers.length - 1 - i;
            final NeuronLayer neuronLayer = neuronLayers[index];
            final double[] intermediateInput = middleResult[index];
            target = neuronLayer.train(intermediateInput, target);
        }
    }

    private double[][] computeMiddleResult(final double[] input) {
        final double[][] middleResult = new double[neuronLayers.length + 1][];
        double[] middleInput = input.clone();
        for (int i = 0; i < neuronLayers.length; i++) {
            middleResult[i] = middleInput;
            middleInput = neuronLayers[i].apply(middleInput);
        }
        middleResult[neuronLayers.length] = middleInput;
        return middleResult;
    }

    private double[] computeFirstError(final double[] input, final double... expectedSolution) {
        final double[] diff = new double[expectedSolution.length];
        for (int i = 0; i < expectedSolution.length; i++) {
            diff[i] = expectedSolution[i] - input[i];
        }
        return diff;
    }

    @Override
    public int inputSize() {
        return neuronLayers[0].inputSize();
    }

    @Override
    public int outputSize() {
        return neuronLayers[neuronLayers.length - 1].neuronNumber();
    }

    private AggregationFunction function() {
        return this.neuronLayers[0].function();
    }

    public NeuronLayer[] getNeuronLayers() {
        return neuronLayers;
    }

    @Override
    public String toString() {
        return "NeuronSystemImpl" + Arrays.toString(neuronLayers);
    }
}
