package com.vgalloy.neuron.neuronsystem;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;

import com.vgalloy.neuron.neuron.Neuron;
import com.vgalloy.neuron.neuron.Neurons;
import com.vgalloy.neuron.neuronlayer.NeuronLayer;
import com.vgalloy.neuron.neuronlayer.NeuronLayers;

/**
 * Created by Vincent Galloy on 22/04/17.
 *
 * @author Vincent Galloy
 */
public final class XorTest {

    @Test
    @Ignore
    public void testSimple() {
        // GIVEN
        final NeuronSystem neuronSystem = new NeuronSystemBuilder(2, 3)
            .addLayer(2)
            .addLayer(1)
            .build();

        // WHEN
        neuronSystem.trainWithBoolean(Arrays.asList(Boolean.TRUE, Boolean.TRUE), Collections.singletonList(false));

        // THEN
        Assert.assertFalse(neuronSystem.apply(Arrays.asList(true, true)).get(0));
        Assert.assertTrue(neuronSystem.apply(Arrays.asList(true, false)).get(0));
        Assert.assertTrue(neuronSystem.apply(Arrays.asList(false, true)).get(0));
        Assert.assertFalse(neuronSystem.apply(Arrays.asList(false, false)).get(0));
    }

    @Test
    @Ignore
    public void xor() {
        // GIVEN
        final NeuronSystem neuronSystem = new NeuronSystemBuilder(2, 2)
            .addLayer(1)
            .build();

        // WHEN
        for (int i = 0; i < 1_000; i++) {
            neuronSystem.trainWithBoolean(Arrays.asList(Boolean.TRUE, Boolean.TRUE), Collections.singletonList(false));
            System.out.println(neuronSystem);
            neuronSystem.trainWithBoolean(Arrays.asList(Boolean.TRUE, Boolean.FALSE), Collections.singletonList(true));
            System.out.println(neuronSystem);
            neuronSystem.trainWithBoolean(Arrays.asList(Boolean.FALSE, Boolean.TRUE), Collections.singletonList(true));
            System.out.println(neuronSystem);
            neuronSystem.trainWithBoolean(Arrays.asList(Boolean.FALSE, Boolean.FALSE), Collections.singletonList(false));
            System.out.println(neuronSystem);
        }

        // THEN
        Assert.assertFalse(neuronSystem.apply(Arrays.asList(true, true)).get(0));
        Assert.assertTrue(neuronSystem.apply(Arrays.asList(true, false)).get(0));
        Assert.assertTrue(neuronSystem.apply(Arrays.asList(false, true)).get(0));
        Assert.assertFalse(neuronSystem.apply(Arrays.asList(false, false)).get(0));
    }

    @Test
    public void xorWithPreFilledNeuron() {
        final NeuronSystem neuronSystem = new NeuronSystemImpl(Arrays.asList(
            NeuronLayers.of(Arrays.asList(Neurons.of(3d, Arrays.asList(-2d, -2d)), Neurons.of(0d, Arrays.asList(1d, 1d)))),
            NeuronLayers.of(Arrays.asList(Neurons.of(-3d, Arrays.asList(2d, 2d)))
        )));

        Assert.assertFalse(neuronSystem.apply(Arrays.asList(true, true)).get(0));
        Assert.assertTrue(neuronSystem.apply(Arrays.asList(true, false)).get(0));
        Assert.assertTrue(neuronSystem.apply(Arrays.asList(false, true)).get(0));
        Assert.assertFalse(neuronSystem.apply(Arrays.asList(false, false)).get(0));
    }
}
