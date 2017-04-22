package com.vgalloy.neuron.full;

import java.util.Arrays;
import java.util.Collections;

import com.vgalloy.neuron.neuronsystem.NeuronSystem;
import com.vgalloy.neuron.neuronsystem.NeuronSystemBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Vincent Galloy on 22/04/17.
 *
 * @author Vincent Galloy
 */
public final class XorTest {

    @Test
    public void test() {
        // GIVEN
        NeuronSystem neuronSystem = new NeuronSystemBuilder(2)
            .addLayer(3)
            .addLayer(2)
            .addLayer(1)
            .build();

        // WHEN
        for (int i = 0; i < 10; i++) {
            neuronSystem.train(Arrays.asList(Boolean.TRUE, Boolean.TRUE), Collections.singletonList(false));
            neuronSystem.train(Arrays.asList(Boolean.TRUE, Boolean.FALSE), Collections.singletonList(true));
            neuronSystem.train(Arrays.asList(Boolean.FALSE, Boolean.TRUE), Collections.singletonList(true));
            neuronSystem.train(Arrays.asList(Boolean.FALSE, Boolean.FALSE), Collections.singletonList(false));
        }

        // THEN
        Assert.assertFalse(neuronSystem.compute(Arrays.asList(true, true)).get(0));
        Assert.assertTrue(neuronSystem.compute(Arrays.asList(true, false)).get(0));
        Assert.assertTrue(neuronSystem.compute(Arrays.asList(false, true)).get(0));
        Assert.assertFalse(neuronSystem.compute(Arrays.asList(false, false)).get(0));
    }
}
