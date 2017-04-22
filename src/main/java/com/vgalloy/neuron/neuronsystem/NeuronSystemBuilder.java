package com.vgalloy.neuron.neuronsystem;

import java.util.ArrayList;
import java.util.List;

import com.vgalloy.neuron.neuronlayer.NeuronLayer;
import com.vgalloy.neuron.neuronlayer.NeuronLayers;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public final class NeuronSystemBuilder {

    private final List<NeuronLayer> neuronLayers = new ArrayList<>();
    private final int neuronSystemParameterNumber;

    public NeuronSystemBuilder(int neuronSystemParameterNumber) {
        if (neuronSystemParameterNumber < 1) {
            throw new IllegalArgumentException("System should at least get one argument");
        }
        this.neuronSystemParameterNumber = neuronSystemParameterNumber;
    }

    public NeuronSystemBuilder addLayer(int numberOfNeuron) {
        if (numberOfNeuron <= 0) {
            throw new IllegalArgumentException("number of neuron must be > 0");
        }
        final int size;
        if (neuronLayers.isEmpty()) {
            size = neuronSystemParameterNumber;
        } else {
            size = neuronLayers.get(neuronLayers.size() - 1).size();
        }

        NeuronLayer neuronLayer = NeuronLayers.of(size, numberOfNeuron);

        neuronLayers.add(neuronLayer);

        return this;
    }

    public NeuronSystem build() {
        return new NeuronSystemImpl(neuronLayers);
    }
}
