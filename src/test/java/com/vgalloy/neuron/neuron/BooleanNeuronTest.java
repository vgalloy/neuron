package com.vgalloy.neuron.neuron;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.vgalloy.neuron.util.BooleanFunction;
import com.vgalloy.neuron.util.BooleanNeuron;

/**
 * Created by Vincent Galloy on 26/09/2018.
 *
 * @author Vincent Galloy
 */
public final class BooleanNeuronTest {

    @Test
    public void and() {
        NeuronTestHelper.validate(BooleanNeuron.AND, BooleanFunction.AND);
    }

    @Test
    public void or() {
        NeuronTestHelper.validate(BooleanNeuron.OR, BooleanFunction.OR);
    }

    @Test
    public void first() {
        NeuronTestHelper.validate(BooleanNeuron.FIRST, BooleanFunction.FIRST);
    }

    @Test
    public void second() {
        NeuronTestHelper.validate(BooleanNeuron.SECOND, BooleanFunction.SECOND);
    }

    @Test
    public void notAnd() {
        NeuronTestHelper.validate(BooleanNeuron.NOT_AND, BooleanFunction.NOT_AND);
    }

    @Test
    public void atLeastOneFromOne() {
        final Neuron neuron = BooleanNeuron.atLeast(1, 1).apply(0);
        final boolean result = neuron.apply(Arrays.asList(true));
        Assert.assertTrue(result);
    }

    @Test
    public void atLeastOneFromTwo() {
        final Neuron neuron = BooleanNeuron.atLeast(2, 1).apply(0);
        Assert.assertTrue(neuron.apply(Arrays.asList(true, false)));
        Assert.assertFalse(neuron.apply(Arrays.asList(false, true)));
    }

    @Test
    public void atLeastOneFromThree() {
        final Neuron neuron = BooleanNeuron.atLeast(3, 1).apply(1);
        Assert.assertFalse(neuron.apply(Arrays.asList(true, false, false)));
        Assert.assertTrue(neuron.apply(Arrays.asList(false, true, false)));
        Assert.assertFalse(neuron.apply(Arrays.asList(true, false, true)));
    }

    @Test
    public void atLeastFourFromFive() {
        final Neuron neuron = BooleanNeuron.atLeast(5, 4).apply(0, 1, 2, 3, 4);
        Assert.assertFalse(neuron.apply(Arrays.asList(true, false, false, false, false)));
        Assert.assertFalse(neuron.apply(Arrays.asList(false, true, false, false, false)));
        Assert.assertFalse(neuron.apply(Arrays.asList(false, false, true, true, false)));
        Assert.assertFalse(neuron.apply(Arrays.asList(true, true, false, false, true)));
        Assert.assertTrue(neuron.apply(Arrays.asList(true, true, true, false, true)));
        Assert.assertTrue(neuron.apply(Arrays.asList(true, true, true, true, true)));
    }
}
