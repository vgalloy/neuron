package com.vgalloy.neuron.neuronsystem;

import java.util.ArrayList;
import java.util.List;

import com.vgalloy.neuron.neuronlayer.NeuronLayer;
import com.vgalloy.neuron.neuronlayer.NeuronLayers;
import com.vgalloy.neuron.util.NeuronAssert;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public final class NeuronSystemBuilder {

    private final List<NeuronLayer> neuronLayers = new ArrayList<>();

    /**
     * @param neuronSystemParameterNumber the number of input
     * @param numberOfNeuron              the number of neuron in the first layer
     */
    public NeuronSystemBuilder(int neuronSystemParameterNumber, int numberOfNeuron) {
        NeuronAssert.checkState(0 < neuronSystemParameterNumber, "System should at least get one argument.");
        final NeuronLayer neuronLayer = NeuronLayers.of(neuronSystemParameterNumber, numberOfNeuron);
        neuronLayers.add(neuronLayer);
    }

    public NeuronSystemBuilder addLayer(int numberOfNeuron) {
        NeuronAssert.checkState(0 < numberOfNeuron, "number of neuron must be > 0.");

        final int size = neuronLayers.get(neuronLayers.size() - 1).neuronNumber();
        final NeuronLayer neuronLayer = NeuronLayers.of(size, numberOfNeuron);
        neuronLayers.add(neuronLayer);

        return this;
    }

    public NeuronSystem build() {
        return new NeuronSystemImpl(neuronLayers);
    }
}
