package com.vgalloy.neuron.neuronsystem;

import java.util.Arrays;
import java.util.Objects;

import com.vgalloy.neuron.constant.Constant;
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
    public boolean[] apply(final boolean... booleans) {
        boolean[] list = booleans;
        for (final NeuronLayer neuronLayer : neuronLayers) {
            list = neuronLayer.applyBoolean(list);
        }
        return list;
    }

    @Override
    public void trainWithBoolean(final boolean[] input, final boolean... expectedSolution) {
        NeuronAssert.state(outputSize() == expectedSolution.length, "Expected solutions size is : " + expectedSolution.length + " must be " + input.length);

        final boolean[][] middleResult = computeMiddleResult(input);
        final boolean[] output = middleResult[neuronLayers.length];
        double[] diff = computeFirstError(output, expectedSolution);

        // back prop
        for (int i = 0; i < neuronLayers.length; i++) {
            final int index = neuronLayers.length - 1 - i;
            final NeuronLayer neuronLayer = neuronLayers[index];
            final boolean[] intermediateInput = middleResult[index];
            diff = neuronLayer.trainWithDouble(intermediateInput, diff);
        }
    }

    private boolean[][] computeMiddleResult(final boolean[] input) {
        final boolean[][] middleResult = new boolean[neuronLayers.length + 1][];
        boolean[] middleInput = input.clone();
        for (int i = 0; i < neuronLayers.length; i++) {
            middleResult[i] = middleInput;
            middleInput = neuronLayers[i].applyBoolean(middleInput);
        }
        middleResult[neuronLayers.length] = middleInput;
        return middleResult;
    }

    private double[] computeFirstError(final boolean[] input, final boolean... expectedSolution) {
        final double[] diff = new double[expectedSolution.length];
        for (int i = 0; i < expectedSolution.length; i++) {
            diff[i] = Constant.toDouble(expectedSolution[i]) - Constant.toDouble(input[i]);
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

    public NeuronLayer[] getNeuronLayers() {
        return neuronLayers;
    }

    @Override
    public String toString() {
        return "NeuronSystemImpl" + Arrays.toString(neuronLayers);
    }
}
