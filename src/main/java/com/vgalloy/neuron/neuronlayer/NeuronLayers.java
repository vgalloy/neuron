package com.vgalloy.neuron.neuronlayer;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vgalloy.neuron.neuron.Neuron;
import com.vgalloy.neuron.neuron.Neurons;
import com.vgalloy.neuron.util.NeuronAssert;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public final class NeuronLayers {

    private NeuronLayers() {
        throw new AssertionError();
    }

    public static NeuronLayer of(int previousLayerSize, int layerSize) {
        NeuronAssert.checkState(0 < previousLayerSize, "Can not create a neuron layer with no entry");
        NeuronAssert.checkState(0 < layerSize, "Can not create a neuron layer with no neuron");

        List<Neuron> neurons = Stream
            .generate(() -> Neurons.of(previousLayerSize))
            .limit(layerSize)
            .collect(Collectors.toList());

        return of(neurons);
    }

    public static NeuronLayer of(List<Neuron> neurons) {
        Objects.requireNonNull(neurons);

        return new NeuronLayerImpl(neurons);
    }
}
