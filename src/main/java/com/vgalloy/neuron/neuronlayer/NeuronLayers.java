package com.vgalloy.neuron.neuronlayer;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.vgalloy.neuron.neuron.Neuron;
import com.vgalloy.neuron.neuron.Neurons;

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
        if (previousLayerSize <= 0) {
            throw new IllegalArgumentException("Can not create a neuron layer with no entry");
        }

        if (layerSize <= 0) {
            throw new IllegalArgumentException("Can not create a neuron layer with no neuron");
        }

        List<Neuron> neurons = IntStream.range(0, layerSize)
            .boxed()
            .map(e -> Neurons.of(previousLayerSize))
            .collect(Collectors.toList());

        return of(neurons);
    }

    public static NeuronLayer of(List<Neuron> neurons) {
        Objects.requireNonNull(neurons);

        return new NeuronLayerImpl(neurons);
    }
}
