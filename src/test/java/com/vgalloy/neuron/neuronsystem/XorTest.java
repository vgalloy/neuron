package com.vgalloy.neuron.neuronsystem;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;

import com.vgalloy.neuron.neuron.BooleanNeuron;
import com.vgalloy.neuron.neuronlayer.NeuronLayers;

/**
 * Created by Vincent Galloy on 22/04/17.
 *
 * @author Vincent Galloy
 */
public final class XorTest {

    @Test
    public void testSimple() {
        // GIVEN
        final NeuronSystem neuronSystem = new NeuronSystemBuilder(2, 3)
            .addLayer(2)
            .addLayer(1)
            .build();

        // WHEN
        train(neuronSystem);

        // THEN
        validate(neuronSystem);
    }

    @Test
    public void xor() {
        // GIVEN
        final NeuronSystem neuronSystem = new NeuronSystemBuilder(2, 2)
            .addLayer(1)
            .build();

        // WHEN
        train(neuronSystem);

        // THEN
        validate(neuronSystem);
    }

    @Test
    public void xorWithPreFilledNeuron() {
        final NeuronSystem neuronSystem = new NeuronSystemImpl(Arrays.asList(
            NeuronLayers.of(BooleanNeuron.MAX_ONE, BooleanNeuron.OR),
            NeuronLayers.of(BooleanNeuron.AND)
        ));

        validate(neuronSystem);
    }

    private static void train(final NeuronSystem neuronSystem) {
        for (int i = 0; i < 100; i++) {
            neuronSystem.trainWithBoolean(Arrays.asList(Boolean.TRUE, Boolean.TRUE), Collections.singletonList(false));
            neuronSystem.trainWithBoolean(Arrays.asList(Boolean.TRUE, Boolean.FALSE), Collections.singletonList(true));
            neuronSystem.trainWithBoolean(Arrays.asList(Boolean.FALSE, Boolean.TRUE), Collections.singletonList(true));
            neuronSystem.trainWithBoolean(Arrays.asList(Boolean.FALSE, Boolean.FALSE), Collections.singletonList(false));
        }
    }

    private static void validate(final NeuronSystem neuronSystem) {
        Assert.assertFalse(neuronSystem.apply(Arrays.asList(true, true)).get(0));
        Assert.assertTrue(neuronSystem.apply(Arrays.asList(true, false)).get(0));
        Assert.assertTrue(neuronSystem.apply(Arrays.asList(false, true)).get(0));
        Assert.assertFalse(neuronSystem.apply(Arrays.asList(false, false)).get(0));
    }
}
