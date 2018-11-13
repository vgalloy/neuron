package com.vgalloy.neuron.neuron;

import org.junit.Assert;
import org.junit.Test;

import com.vgalloy.neuron.constant.Constant;
import com.vgalloy.neuron.neuron.builder.LengthBuilder;
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
        final BooleanConverter converter = lengthBuilder.getFunction();
        final Neuron neuron = lengthBuilder.withCoefficient(0d, 1d).build();

        // WHEN
        final double[] result = neuron.train(false, true);

        // THEN
        Assert.assertEquals(1, result.length);
        Assert.assertEquals(converter.falseValue() - converter.trueValue(), result[0], 0.0001);
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
        NeuronTestHelper.buildTest(BooleanFunction.AND, Neurons.linear());
    }

    @Test
    public void or() {
        NeuronTestHelper.buildTest(BooleanFunction.OR, Neurons.linear());
    }

    @Test
    public void first() {
        NeuronTestHelper.buildTest(BooleanFunction.FIRST, Neurons.linear());
    }

    @Test
    public void notSecond() {
        NeuronTestHelper.buildTest(BooleanFunction.not(BooleanFunction.SECOND), Neurons.linear());
    }

    @Test
    public void neuronIsImproving() {
        // GIVEN
        final Neuron neuron = Neurons.linear().withCoefficient(0.1, 0.1).build();

        // WHEN
        final double result1 = neuron.apply(true);
        neuron.train(true, true);
        final double result2 = neuron.apply(true);

        // THEN
        Assert.assertTrue(result1 < result2);
    }
}
