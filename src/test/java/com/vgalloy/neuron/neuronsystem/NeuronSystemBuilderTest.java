package com.vgalloy.neuron.neuronsystem;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.vgalloy.neuron.neuronlayer.NeuronLayer;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Vincent Galloy on 22/04/17.
 *
 * @author Vincent Galloy
 */
public final class NeuronSystemBuilderTest {

    @Test
    public void neuronSystemCreator() {
        // GIVEN
        final NeuronSystemImpl neuronSystem = (NeuronSystemImpl) new NeuronSystemBuilder(2)
            .addLayer(3)
            .addLayer(2)
            .addLayer(1)
            .build();

        // WHEN
        final List<NeuronLayer> neuronLayers = neuronSystem.getNeuronLayers();

        // THEN
        Assert.assertEquals(3, neuronLayers.size());
    }

    @Test
    public void outputSize() {
        // GIVEN
        final NeuronSystem neuronSystem = new NeuronSystemBuilder(3)
            .addLayer(10)
            .addLayer(2)
            .addLayer(2)
            .addLayer(5)
            .addLayer(4)
            .build();

        // THEN
        for (int i = 1; i < 10; i++) {
            test(neuronSystem, i, 3 != i);
        }
    }

    private void test(final NeuronSystem neuronSystem, final int inputSize, final boolean shouldFail) {
        final List<Boolean> input = IntStream.range(0, inputSize)
            .boxed()
            .map(e -> Boolean.FALSE)
            .collect(Collectors.toList());
        try {
            neuronSystem.apply(input);
            if (shouldFail) {
                Assert.fail("Should fail");
            }
        } catch (Exception e) {
            if (!shouldFail) {
                Assert.fail("Should not fail");
            }
        }
    }
}
