package com.vgalloy.neuron.neuron;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.vgalloy.neuron.constant.Constant;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public final class SimpleNeuronTest {

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
        Neuron neuron = Neurons.of(0L, Collections.singletonList(Constant.ONE));

        // WHEN
        final boolean result = neuron.apply(Collections.singletonList(true));

        // THEN
        Assert.assertTrue(result);
    }

    @Test
    public void neuronTrain() {
        // GIVEN
        final Neuron neuron = Neurons.of(Constant.MINUS_ONE, Collections.singletonList(Constant.ONE));

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
        final Neuron neuron = Neurons.of(24L, Arrays.asList(-88L, -37L));

        // WHEN
        final List<Double> result = neuron.train(Arrays.asList(false, false), true);

        // THEN
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void neuronTrainResult() {
        // GIVEN
        final Neuron neuron = Neurons.of(0L, Collections.singletonList(Constant.ONE));

        // WHEN
        final List<Double> result = neuron.train(Collections.singletonList(true), false);

        // THEN
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(2d * Constant.MINUS_ONE, result.get(0), 0.0001);
    }

    @Test
    public void simpleLearning() {
        // GIVEN
        final Neuron neuron = Neurons.of(Collections.singletonList(Constant.ONE));

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
        Neuron neuron = Neurons.of(Constant.ONE, Arrays.asList(Constant.ONE, Constant.ONE));

        // WHEN
        final List<Double> result = neuron.train(Arrays.asList(true, false), false);

        // THEN
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void and() {
        NeuronTestHelper.buildTest((a, b) -> a && b);
    }

    @Test
    public void or() {
        NeuronTestHelper.buildTest((a, b) -> a || b);
    }

    @Test
    public void first() {
        NeuronTestHelper.buildTest((a, b) -> a);
    }

    @Test
    public void notSecond() {
        NeuronTestHelper.buildTest((a, b) -> !b);
    }
}
