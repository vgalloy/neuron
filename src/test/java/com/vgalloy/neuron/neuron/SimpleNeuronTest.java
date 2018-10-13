package com.vgalloy.neuron.neuron;

import org.junit.Assert;
import org.junit.Test;

import com.vgalloy.neuron.constant.Constant;
import com.vgalloy.neuron.util.BooleanFunction;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public final class SimpleNeuronTest {

    @Test
    public void correctMonoNeuron() {
        // GIVEN
        final Neuron neuron = new SimpleNeuron(Constant.FALSE, Constant.FALSE);

        // WHEN
        final boolean result = neuron.apply(true);

        // THEN
        Assert.assertFalse(result);
    }

    @Test
    public void correctMonoNeuron2() {
        // GIVEN
        final Neuron neuron = new SimpleNeuron(0d, Constant.TRUE);

        // WHEN
        final boolean result = neuron.apply(true);

        // THEN
        Assert.assertTrue(result);
    }

    @Test
    public void neuronTrain() {
        // GIVEN
        final Neuron neuron = new SimpleNeuron(Constant.FALSE, Constant.TRUE);

        // WHEN
        neuron.train(false, true);
        neuron.train(false, true);
        neuron.train(false, true);
        neuron.train(false, true);
        neuron.train(false, true);
        final boolean result = neuron.apply(true);

        // THEN
        Assert.assertFalse(result);
    }

    @Test
    public void neuronTrainResultSize() {
        // GIVEN
        final Neuron neuron = new SimpleNeuron(0.24d, -0.88d, -0.37d);

        // WHEN
        final double[] result = neuron.train(true, false, false);

        // THEN
        Assert.assertEquals(2, result.length);
    }

    @Test
    public void neuronTrainResult() {
        // GIVEN
        final Neuron neuron = new SimpleNeuron(0d, Constant.TRUE);

        // WHEN
        final double[] result = neuron.train(false, true);

        // THEN
        Assert.assertEquals(1, result.length);
        Assert.assertEquals(Constant.FALSE - Constant.TRUE, result[0], 0.0001);
    }

    @Test
    public void simpleLearning() {
        // GIVEN
        final Neuron neuron = new SimpleNeuron(Constant.doubleRandom(), 1d);

        // WHEN
        neuron.train(false, true);
        neuron.train(false, true);
        neuron.train(true, true);
        final boolean result = neuron.apply(true);

        // THEN
        Assert.assertTrue(result);
    }

    @Test
    public void correctionListSize() {
        // GIVEN
        Neuron neuron = new SimpleNeuron(1d, 1d, 1d);

        // WHEN
        final double[] result = neuron.train(false, true, false);

        // THEN
        Assert.assertEquals(2, result.length);
    }

    @Test
    public void and() {
        NeuronTestHelper.buildTest(BooleanFunction.AND, SimpleNeuronTest::build);
    }

    @Test
    public void or() {
        NeuronTestHelper.buildTest(BooleanFunction.OR, SimpleNeuronTest::build);
    }

    @Test
    public void first() {
        NeuronTestHelper.buildTest(BooleanFunction.FIRST, SimpleNeuronTest::build);
    }

    @Test
    public void notSecond() {
        NeuronTestHelper.buildTest(BooleanFunction.not(BooleanFunction.SECOND), SimpleNeuronTest::build);
    }

    private static Neuron build(final boolean value1, final boolean value2, final boolean value3) {
        return new SimpleNeuron(Constant.mapBoolean(value1), Constant.mapBoolean(value2, value3));
    }
}
