package com.vgalloy.neuron.neuronsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        List<Long> list = expectedSolution.stream()
            .map(e -> e ? Constant.ONE : Constant.MINUS_ONE)
            .collect(Collectors.toList());

        for (NeuronLayer neuronLayer : neuronLayers) {
            list = neuronLayer.trainWithLong(input, list);
        }

        if (expectedSolution.size() != list.size()) {
            throw new IllegalArgumentException("Expected solution should have same length than result");
        }

        for (int i = 0; i < list.size(); i++) {

        }

//        List<Boolean> list = new ArrayList<>(expectedSolution);
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
