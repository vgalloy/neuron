package com.vgalloy.neuron.neuronsystem;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.vgalloy.neuron.neuron.Neurons;
import com.vgalloy.neuron.neuronlayer.NeuronLayer;

/**
 * Created by Vincent Galloy on 22/04/17.
 *
 * @author Vincent Galloy
 */
public final class NeuronSystemBuilderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void neuronSystemCreator() {
        // GIVEN
        final NeuronSystemImpl neuronSystem = (NeuronSystemImpl) new NeuronSystemBuilder(Neurons.tanh(), 2, 3)
            .addLayer(2)
            .addLayer(1)
            .build();

        // WHEN
        final NeuronLayer[] neuronLayers = neuronSystem.getNeuronLayers();

        // THEN
        Assert.assertEquals(3, neuronLayers.length);
    }

    @Test
    public void wrongOutputSize() {
        // GIVEN
        final NeuronSystem neuronSystem = new NeuronSystemBuilder(Neurons.tanh(), 3, 10)
            .addLayer(2)
            .addLayer(2)
            .addLayer(4)
            .build();
        final boolean[] input = new boolean[5];

        // EXCEPTION
        expectedException.expect(IllegalArgumentException.class);

        // THEN
        neuronSystem.applyBoolean(input);
    }

    @Test
    public void correctOutputSize() {
        // GIVEN
        final NeuronSystem neuronSystem = new NeuronSystemBuilder(Neurons.tanh(), 3, 10)
            .addLayer(4)
            .addLayer(4)
            .addLayer(4)
            .addLayer(2)
            .build();
        final boolean[] input = new boolean[3];

        // THEN
        neuronSystem.applyBoolean(input);
    }
}
