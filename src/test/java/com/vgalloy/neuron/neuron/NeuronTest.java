package com.vgalloy.neuron.neuron;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.vgalloy.neuron.constant.Constant;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public final class NeuronTest {

    @Test
    public void correctMonoNeuron() {
        // GIVEN
        Neuron neuron = Neurons.of(Constant.MINUS_ONE, Collections.singletonList(Constant.ONE));

        // WHEN
        final boolean result = neuron.apply(Collections.singletonList(true));

        // THEN
        Assert.assertFalse(result);
    }

    @Test
    public void correctMonoNeuron2() {
        // GIVEN
        Neuron neuron = Neurons.of(Constant.MINUS_ONE, Collections.singletonList(Constant.ONE * 2L));

        // WHEN
        final boolean result = neuron.apply(Collections.singletonList(true));

        // THEN
        Assert.assertTrue(result);
    }

    @Test
    public void neuronTrain() {
        // GIVEN
        final Neuron neuron = Neurons.of(Constant.MINUS_ONE, Collections.singletonList(Constant.ONE * 2L));

        // WHEN
        neuron.train(Collections.singletonList(true), false);
        neuron.train(Collections.singletonList(true), false);
        final boolean result = neuron.apply(Collections.singletonList(true));

        // THEN
        Assert.assertFalse(result);
    }

    @Test
    public void neuronTrainResultSize() {
        // GIVEN
        Neuron neuron = Neurons.of(24L, Arrays.asList(-88L, -37L));

        // WHEN
        final List<Long> result = neuron.train(Arrays.asList(false, false), true);

        // THEN
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void neuronTrainResult() {
        // GIVEN
        Neuron neuron = Neurons.of(Constant.MINUS_ONE, Collections.singletonList(Constant.ONE * 2L));

        // WHEN
        final List<Long> result = neuron.train(Collections.singletonList(true), false);

        // THEN
        Assert.assertEquals(1, result.size());
        for (Long aResult : result) {
            Assert.assertEquals(6 * Constant.MINUS_ONE / 10, (long) aResult);
        }
    }

    @Test
    public void simpleLearning() {
        // GIVEN
        Neuron neuron = Neurons.of(Collections.singletonList(Constant.ONE * 2L));

        // WHEN
        neuron.train(Collections.singletonList(true), false);
        neuron.train(Collections.singletonList(true), false);
        neuron.train(Collections.singletonList(true), true);
        final boolean result = neuron.apply(Collections.singletonList(true));

        // THEN
        Assert.assertTrue(result);
    }

    @Test(expected = NullPointerException.class)
    public void creationFail() {
        // GIVEN
        Neurons.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creationFail2() {
        // GIVEN
        Neurons.of(Collections.emptyList());
    }

    @Test
    public void correctionListSize() {
        // GIVEN
        Neuron neuron = Neurons.of(1L, Arrays.asList(1L, 1L));

        // WHEN
        final List<Long> result = neuron.train(Arrays.asList(true, false), false);

        // THEN
        Assert.assertEquals(2, result.size());
    }
}
