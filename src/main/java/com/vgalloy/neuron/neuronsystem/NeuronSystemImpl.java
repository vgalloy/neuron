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
        for (int i = 0; i < neuronLayers.length; i++) {
            list = neuronLayers[i].apply(list);
        }
        return list;
    }

    @Override
    public boolean trainWithBoolean(boolean[] input, final boolean... expectedSolution) {
        final boolean[][] middleResult = new boolean[neuronLayers.length][];
        for (int i = 0; i < neuronLayers.length; i++) {
            middleResult[i] = input;
            input = neuronLayers[i].apply(input);
        }
        NeuronAssert.state(input.length == expectedSolution.length, "Expected solutions size is : " + expectedSolution.length + " must be " + input.length);

        double[] diff = new double[expectedSolution.length];
        boolean result = true;
        for (int i = 0; i < expectedSolution.length; i++) {
            result = result && expectedSolution[i] == input[i];
            diff[i] = Constant.toDouble(expectedSolution[i]) - Constant.toDouble(input[i]);
        }

        for (int i = 0; i < neuronLayers.length; i++) {
            final int index = neuronLayers.length - 1 - i;
            final NeuronLayer neuronLayer = neuronLayers[index];
            final boolean[] intermediateInput = middleResult[index];
            diff = neuronLayer.trainWithDouble(intermediateInput, diff);
        }

        return result;
    }

    @Override
    public int inputSize() {
        return neuronLayers[0].inputSize();
    }

    @Override
    public int outputSize() {
        return neuronLayers[neuronLayers.length - 1].inputSize();
    }

    public NeuronLayer[] getNeuronLayers() {
        return neuronLayers;
    }

    @Override
    public String toString() {
        return "NeuronSystemImpl" + Arrays.toString(neuronLayers);
    }
}
