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
        final Neuron neuron = new SimpleNeuron(Constant.FALSE, Collections.singletonList(Constant.TRUE));

        // WHEN
        final boolean result = neuron.apply(Collections.singletonList(true));

        // THEN
        Assert.assertFalse(result);
    }

    @Test
    public void correctMonoNeuron2() {
        // GIVEN
        final Neuron neuron = new SimpleNeuron(0d, Collections.singletonList(Constant.TRUE));

        // WHEN
        final boolean result = neuron.apply(Collections.singletonList(true));

        // THEN
        Assert.assertTrue(result);
    }

    @Test
    public void neuronTrain() {
        // GIVEN
        final Neuron neuron = new SimpleNeuron(Constant.FALSE, Collections.singletonList(Constant.TRUE));

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
        final Neuron neuron = new SimpleNeuron(0.24d, Arrays.asList(-0.88d, -0.37d));

        // WHEN
        final List<Double> result = neuron.train(Arrays.asList(false, false), true);

        // THEN
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void neuronTrainResult() {
        // GIVEN
        final Neuron neuron = new SimpleNeuron(0d, Collections.singletonList(Constant.TRUE));

        // WHEN
        final List<Double> result = neuron.train(Collections.singletonList(true), false);

        // THEN
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(2 * Constant.FALSE, result.get(0), 0.0001);
    }

    @Test
    public void simpleLearning() {
        // GIVEN
        final Neuron neuron = new SimpleNeuron(Constant.doubleRandom(), Collections.singletonList(1d));

        // WHEN
        neuron.train(Collections.singletonList(true), false);
        neuron.train(Collections.singletonList(true), false);
        neuron.train(Collections.singletonList(true), true);
        final boolean result = neuron.apply(Collections.singletonList(true));

        // THEN
        Assert.assertTrue(result);
    }

    @Test
    public void correctionListSize() {
        // GIVEN
        Neuron neuron = new SimpleNeuron(1d, Arrays.asList(1d, 1d));

        // WHEN
        final List<Double> result = neuron.train(Arrays.asList(true, false), false);

        // THEN
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void and() {
        NeuronTestHelper.buildTest((a, b) -> a && b, SimpleNeuronTest::build);
    }

    @Test
    public void or() {
        NeuronTestHelper.buildTest((a, b) -> a || b,  SimpleNeuronTest::build);
    }

    @Test
    public void first() {
        NeuronTestHelper.buildTest((a, b) -> a,  SimpleNeuronTest::build);
    }

    @Test
    public void notSecond() {
        NeuronTestHelper.buildTest((a, b) -> !b,  SimpleNeuronTest::build);
    }

    private static Neuron build(final boolean value1, final boolean value2, final boolean value3) {
        return new SimpleNeuron(Constant.mapBoolean(value1), Constant.mapBoolean(Arrays.asList(value2, value3)));
    }
}
