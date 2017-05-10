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
class NeuronSystemImpl implements NeuronSystem {

    private final List<NeuronLayer> neuronLayers;

    NeuronSystemImpl(List<NeuronLayer> neuronLayers) {
        this.neuronLayers = Objects.requireNonNull(neuronLayers);
    }

    @Override
    public List<Boolean> compute(List<Boolean> input) {
        List<Boolean> list = new ArrayList<>(input);
        for (NeuronLayer neuronLayer : neuronLayers) {
            list = neuronLayer.compute(list);
        }
        return list;
    }

    @Override
    public void train(List<Boolean> input, List<Boolean> expectedSolution) {
        List<Long> expectedSolutionAsLong = Constant.map(expectedSolution);

        List<List<Boolean>> middleResult = new ArrayList<>();
        for (NeuronLayer neuronLayer : neuronLayers) {
            middleResult.add(input);
            input = neuronLayer.compute(input);
        }

        for (int i = 0; i < neuronLayers.size(); i++) {
            int index = neuronLayers.size() - 1 - i;
            expectedSolutionAsLong = neuronLayers.get(index).trainWithLong(middleResult.get(index), expectedSolutionAsLong);
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
