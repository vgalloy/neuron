package com.vgalloy.neuron.neuronsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.vgalloy.neuron.constant.Constant;
import com.vgalloy.neuron.neuronlayer.NeuronLayer;

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
            list = neuronLayer.apply(list);
        }
        return list;
    }

    @Override
    public void train(List<Boolean> input, List<Boolean> expectedSolution) {
        List<Double> expectedSolutionAsLong = Constant.mapBoolean(expectedSolution);

        List<List<Boolean>> middleResult = new ArrayList<>();
        for (final NeuronLayer neuronLayer : neuronLayers) {
            middleResult.add(input);
            input = neuronLayer.apply(input);
        }

        for (int i = 0; i < neuronLayers.size(); i++) {
            int index = neuronLayers.size() - 1 - i;
            final NeuronLayer neuronLayer = neuronLayers.get(index);
            final List<Boolean> intermediateInput = middleResult.get(index);
            expectedSolutionAsLong = neuronLayer.trainWithDouble(intermediateInput, expectedSolutionAsLong);
        }
    }

    public List<NeuronLayer> getNeuronLayers() {
        return neuronLayers;
    }

    @Override
    public String toString() {
        return "NeuronSystemImpl{" +
            "neuronLayers=" + neuronLayers +
            '}';
    }
}
