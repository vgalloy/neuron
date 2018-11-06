package com.vgalloy.neuron.neuronlayer;

import java.util.stream.Stream;

import com.vgalloy.neuron.neuron.Neuron;
import com.vgalloy.neuron.neuron.builder.LengthBuilder;
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

    public static NeuronLayer of(final LengthBuilder lengthBuilder, final int previousLayerSize, final int layerSize) {
        NeuronAssert.state(0 < previousLayerSize, "Can not create a neuron layer with no entry");
        NeuronAssert.state(0 < layerSize, "Can not create a neuron layer with no neuron");

        final Neuron[] neurons = Stream
            .generate(() -> lengthBuilder.inputSize(previousLayerSize).build())
            .limit(layerSize)
            .toArray(Neuron[]::new);

        return of(neurons);
    }

    public static NeuronLayer of(final Neuron... neurons) {
        return new NeuronLayerImpl(neurons);
    }
}
