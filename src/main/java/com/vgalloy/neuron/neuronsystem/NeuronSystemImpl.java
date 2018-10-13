package com.vgalloy.neuron.neuronsystem;

import java.util.ArrayList;
import java.util.List;
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

    private final List<NeuronLayer> neuronLayers;

    NeuronSystemImpl(final List<NeuronLayer> neuronLayers) {
        this.neuronLayers = Objects.requireNonNull(neuronLayers);
    }

    @Override
    public List<Boolean> apply(List<Boolean> input) {
        List<Boolean> list = new ArrayList<>(input);
        for (NeuronLayer neuronLayer : neuronLayers) {
            list = Constant.toList(neuronLayer.apply(Constant.toArray(list)));
        }
        return list;
    }

    @Override
    public boolean trainWithBoolean(List<Boolean> input, final List<Boolean> expectedSolution) {
        final List<List<Boolean>> middleResult = new ArrayList<>();
        for (final NeuronLayer neuronLayer : neuronLayers) {
            middleResult.add(input);
            input = Constant.toList(neuronLayer.apply(Constant.toArray(input)));
        }
        NeuronAssert.checkState(input.size() == expectedSolution.size(), "Expected solutions size is : " + expectedSolution.size() + " must be " + input.size());
        List<Double> diff = new ArrayList<>();
        boolean result = true;
        for (int i = 0; i < expectedSolution.size(); i++) {
            result = result && expectedSolution.get(i) == input.get(i);
            diff.add(Constant.mapBoolean(expectedSolution.get(i)) - Constant.mapBoolean(input.get(i)));
        }

        for (int i = 0; i < neuronLayers.size(); i++) {
            final int index = neuronLayers.size() - 1 - i;
            final NeuronLayer neuronLayer = neuronLayers.get(index);
            final List<Boolean> intermediateInput = middleResult.get(index);
            diff = Constant.toList(neuronLayer.trainWithDouble(Constant.toArray(intermediateInput), Constant.toArrayDouble(diff)));
        }

        return result;
    }

    @Override
    public int inputSize() {
        return neuronLayers.get(0).inputSize();
    }

    @Override
    public int outputSize() {
        return neuronLayers.get(neuronLayers.size() - 1).inputSize();
    }

    public List<NeuronLayer> getNeuronLayers() {
        return neuronLayers;
    }

    @Override
    public String toString() {
        return "NeuronSystemImpl" + neuronLayers;
    }
}
