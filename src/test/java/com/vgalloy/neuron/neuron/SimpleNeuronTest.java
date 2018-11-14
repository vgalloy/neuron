package com.vgalloy.neuron.neuron;

import org.junit.Assert;
import org.junit.Test;

import com.vgalloy.neuron.constant.Constant;
import com.vgalloy.neuron.neuron.builder.LengthBuilder;
import com.vgalloy.neuron.util.BiBooleanFunction;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public final class SimpleNeuronTest {

    @Test
    public void correctMonoNeuron() {
        // GIVEN
        final Neuron neuron = Neurons.linear().withCoefficient(false, true).build();

        // WHEN
        final boolean result = neuron.applyBoolean(true);

        // THEN
        Assert.assertFalse(result);
    }

    @Test
    public void correctMonoNeuron2() {
        // GIVEN
        final Neuron neuron = Neurons.linear().withCoefficient(0d, 1d).build();

        // WHEN
        final boolean result = neuron.applyBoolean(true);

        // THEN
        Assert.assertTrue(result);
    }

    @Test
    public void neuronTrain() {
        // GIVEN
        final Neuron neuron = Neurons.linear().withCoefficient(false, true).build();

        // WHEN
        neuron.train(false, true);
        neuron.train(false, true);
        neuron.train(false, true);
        neuron.train(false, true);
        neuron.train(false, true);
        final boolean result = neuron.applyBoolean(true);

        // THEN
        Assert.assertFalse(result);
    }

    @Test
    public void neuronTrainResultSize() {
        // GIVEN
        final Neuron neuron = Neurons.linear().withCoefficient(0.24d, -0.88d, -0.37d).build();

        // WHEN
        final double[] result = neuron.train(true, false, false);

        // THEN
        Assert.assertEquals(2, result.length);
    }

    @Test
    public void neuronTrainResult() {
        // GIVEN
        final LengthBuilder lengthBuilder = Neurons.linear();
        final Neuron neuron = lengthBuilder.withCoefficient(0d, 1d).build();

        // WHEN
        final double[] result = neuron.train(false, true);

        // THEN
        Assert.assertEquals(1, result.length);
        Assert.assertEquals(0.6, result[0], 0.0001);
    }

    @Test
    public void neuronTrainResult2() {
        // GIVEN
        final LengthBuilder lengthBuilder = Neurons.sigmoid();
        final Neuron neuron = lengthBuilder.withCoefficient(0d, 1d).build();

        // WHEN
        final double[] result = neuron.train(true, true);

        // THEN
        Assert.assertEquals(1, result.length);
        Assert.assertEquals(1.0105754185568534, result[0], 0.0001);
    }

    @Test
    public void simpleLearning() {
        // GIVEN
        final Neuron neuron = Neurons.linear().withCoefficient(Constant.doubleRandom(), 1d).build();

        // WHEN
        neuron.train(false, true);
        neuron.train(false, true);
        neuron.train(true, true);
        final boolean result = neuron.applyBoolean(true);

        // THEN
        Assert.assertTrue(result);
    }

    @Test
    public void identity() {
        // GIVEN
        final Neuron neuron = Neurons.tanh().inputSize(1).build();

        // WHEN
        for(int i = 0; i < 100; i++) {
            neuron.train(true, true);
            neuron.train(false, false);
        }

        // THEN
        Assert.assertTrue(neuron.apply(true));
        Assert.assertFalse(neuron.apply(false));
    }

    @Test
    public void correctionListSize() {
        // GIVEN
        Neuron neuron = Neurons.linear().withCoefficient(1d, 1d, 1d).build();

        // WHEN
        final double[] result = neuron.train(false, true, false);

        // THEN
        Assert.assertEquals(2, result.length);
    }

    @Test
    public void and() {
        NeuronTestHelper.buildTest(BiBooleanFunction.AND, Neurons.linear());
    }

    @Test
    public void or() {
        NeuronTestHelper.buildTest(BiBooleanFunction.OR, Neurons.linear());
    }

    @Test
    public void first() {
        NeuronTestHelper.buildTest(BiBooleanFunction.FIRST, Neurons.linear());
    }

    @Test
    public void notSecond() {
        NeuronTestHelper.buildTest(BiBooleanFunction.not(BiBooleanFunction.SECOND), Neurons.linear());
    }

    @Test
    public void neuronIsImproving() {
        // GIVEN
        final Neuron neuron = Neurons.linear().withCoefficient(0.1, 0.1).build();

        // WHEN
        final double result1 = neuron.apply(Neurons.linear().getFunction().trueValue());
        neuron.train(true, true);
        final double result2 = neuron.apply(Neurons.linear().getFunction().trueValue());

        // THEN
        Assert.assertTrue(result1 < result2);
    }
}
