package com.vgalloy.neuron.neuronlayer;

import java.util.Arrays;

import com.vgalloy.neuron.constant.Constant;
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
    public boolean[] apply(final boolean... input) {
        final boolean[] result = new boolean[neurons.length];
        for (int i = 0; i < neurons.length; i++) {
            result[i] = neurons[i].applyBoolean(input);
        }
        return result;
    }

    @Override
    public double[] trainWithDouble(boolean[] input, double[] error) {
        NeuronAssert.state(neurons.length == error.length, "Error vector size must be equals to neuron layer size.");

        final double[][] coefficientCorrections = new double[neurons.length][];
        for (int i = 0; i < neurons.length; i++) {
            final Neuron neuron = neurons[i];
            final double[] correction = neuron.train(error[i], input);
            NeuronAssert.state(correction.length == input.length, "Correction list size should be equals to input list size.");
            coefficientCorrections[i] = correction;
        }

        final double[] result = new double[input.length];
        for (double[] coefficientCorrection : coefficientCorrections) {
            for (int i = 0; i < coefficientCorrection.length; i++) {
                result[i] = result[i] + coefficientCorrection[i];
            }
        }
        return result;
    }

    @Override
    public double[] trainWithBoolean(boolean[] input, boolean[] expectedSolution) {
        final boolean[] result = apply(input);
        final double[] diff = new double[expectedSolution.length];
        for (int i = 0; i < expectedSolution.length; i++) {
            diff[i] = Constant.toDouble(expectedSolution[i]) - Constant.toDouble(result[i]);
        }
        return trainWithDouble(input, diff);
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
    public String toString() {
        return "Layer" + Arrays.toString(neurons);
    }
}
