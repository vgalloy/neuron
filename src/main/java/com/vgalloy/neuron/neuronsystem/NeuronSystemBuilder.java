package com.vgalloy.neuron.neuronsystem;

import java.util.ArrayList;
import java.util.List;

import com.vgalloy.neuron.neuron.builder.LengthBuilder;
import com.vgalloy.neuron.neuronlayer.NeuronLayer;
import com.vgalloy.neuron.neuronlayer.NeuronLayers;
import com.vgalloy.neuron.util.NeuronAssert;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public final class NeuronSystemBuilder {

    private final LengthBuilder lengthBuilder;
    private final List<NeuronLayer> neuronLayers = new ArrayList<>();

    /**
     * @param neuronSystemParameterNumber the number of input
     * @param numberOfNeuron              the number of neuron in the first layer
     */
    public NeuronSystemBuilder(final LengthBuilder lengthBuilder, final int neuronSystemParameterNumber, final int numberOfNeuron) {
        NeuronAssert.state(0 < neuronSystemParameterNumber, "System should at least get one argument.");
        final NeuronLayer neuronLayer = NeuronLayers.of(lengthBuilder, neuronSystemParameterNumber, numberOfNeuron);
        neuronLayers.add(neuronLayer);
        this.lengthBuilder = lengthBuilder;
    }

    public NeuronSystemBuilder addLayer(int numberOfNeuron) {
        NeuronAssert.state(0 < numberOfNeuron, "number of neuron must be > 0.");

        final int size = neuronLayers.get(neuronLayers.size() - 1).neuronNumber();
        final NeuronLayer neuronLayer = NeuronLayers.of(lengthBuilder, size, numberOfNeuron);
        neuronLayers.add(neuronLayer);

        return this;
    }

    public NeuronSystem build() {
        return new NeuronSystemImpl(neuronLayers.toArray(new NeuronLayer[0]));
    }
}
