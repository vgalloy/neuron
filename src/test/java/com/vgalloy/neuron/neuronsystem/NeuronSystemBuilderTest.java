package com.vgalloy.neuron.neuronsystem;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        final NeuronSystemImpl neuronSystem = (NeuronSystemImpl) new NeuronSystemBuilder(2, 3)
            .addLayer(2)
            .addLayer(1)
            .build();

        // WHEN
        final List<NeuronLayer> neuronLayers = neuronSystem.getNeuronLayers();

        // THEN
        Assert.assertEquals(3, neuronLayers.size());
    }

    @Test
    public void wrongOutputSize() {
        // GIVEN
        final NeuronSystem neuronSystem = new NeuronSystemBuilder(3, 10)
            .addLayer(2)
            .addLayer(2)
            .addLayer(4)
            .build();
        final List<Boolean> input = IntStream.range(0, 5)
            .boxed()
            .map(e -> Boolean.FALSE)
            .collect(Collectors.toList());

        // EXCEPTION
        expectedException.expect(IllegalArgumentException.class);

        // THEN
        neuronSystem.apply(input);
    }
}
