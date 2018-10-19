package com.vgalloy.neuron.neuron;

import org.junit.Assert;
import org.junit.Test;

import com.vgalloy.neuron.util.BooleanFunction;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public final class TanHNeuronTest {

    @Test
    public void and() {
        NeuronTestHelper.buildTest(BooleanFunction.AND, Neurons.tanh());
    }

    @Test
    public void or() {
        NeuronTestHelper.buildTest(BooleanFunction.OR, Neurons.tanh());
    }

    @Test
    public void first() {
        NeuronTestHelper.buildTest(BooleanFunction.FIRST, Neurons.tanh());
    }

    @Test
    public void notSecond() {
        NeuronTestHelper.buildTest(BooleanFunction.not(BooleanFunction.SECOND), Neurons.tanh());
    }

    @Test
    public void trainingOutputSize() {
        // GIVEN
        final Neuron neuron = Neurons.tanh().withCoefficient(true, true, true).build();

        // WHEN
        final double[] errors = neuron.train(false, true, true);

        // THEN
        Assert.assertEquals(2, errors.length);
    }
}
