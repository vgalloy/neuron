package com.vgalloy.neuron.neuron;

import org.junit.Assert;
import org.junit.Test;

import com.vgalloy.neuron.util.BiBooleanFunction;
import com.vgalloy.neuron.util.BooleanNeuron;

/**
 * Created by Vincent Galloy on 26/09/2018.
 *
 * @author Vincent Galloy
 */
public final class BooleanNeuronTest {

    @Test
    public void and() {
        NeuronTestHelper.validate(BooleanNeuron.AND, BiBooleanFunction.AND);
    }

    @Test
    public void or() {
        NeuronTestHelper.validate(BooleanNeuron.OR, BiBooleanFunction.OR);
    }

    @Test
    public void first() {
        NeuronTestHelper.validate(BooleanNeuron.FIRST, BiBooleanFunction.FIRST);
    }

    @Test
    public void second() {
        NeuronTestHelper.validate(BooleanNeuron.SECOND, BiBooleanFunction.SECOND);
    }

    @Test
    public void notAnd() {
        NeuronTestHelper.validate(BooleanNeuron.NOT_AND, BiBooleanFunction.NOT_AND);
    }

    @Test
    public void atLeastOneFromOne() {
        final Neuron neuron = BooleanNeuron.atLeast(1, 1).apply(0);
        final boolean result = neuron.applyBoolean(true);
        Assert.assertTrue(result);
    }

    @Test
    public void atLeastOneFromTwo() {
        final Neuron neuron = BooleanNeuron.atLeast(2, 1).apply(0);
        Assert.assertTrue(neuron.applyBoolean(true, false));
        Assert.assertFalse(neuron.applyBoolean(false, true));
    }

    @Test
    public void atLeastOneFromThree() {
        final Neuron neuron = BooleanNeuron.atLeast(3, 1).apply(1);
        Assert.assertFalse(neuron.applyBoolean(true, false, false));
        Assert.assertTrue(neuron.applyBoolean(false, true, false));
        Assert.assertFalse(neuron.applyBoolean(true, false, true));
    }

    @Test
    public void atLeastFourFromFive() {
        final Neuron neuron = BooleanNeuron.atLeast(5, 4).apply(0, 1, 2, 3, 4);
        Assert.assertFalse(neuron.applyBoolean(true, false, false, false, false));
        Assert.assertFalse(neuron.applyBoolean(false, true, false, false, false));
        Assert.assertFalse(neuron.applyBoolean(false, false, true, true, false));
        Assert.assertFalse(neuron.applyBoolean(true, true, false, false, true));
        Assert.assertTrue(neuron.applyBoolean(true, true, true, false, true));
        Assert.assertTrue(neuron.applyBoolean(true, true, true, true, true));
    }
}
