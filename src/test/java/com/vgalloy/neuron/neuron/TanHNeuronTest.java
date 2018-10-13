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
public final class TanHNeuronTest {

    @Test
    public void and() {
        NeuronTestHelper.buildTest(BooleanFunction.AND, TanHNeuronTest::build);
    }

    @Test
    public void or() {
        NeuronTestHelper.buildTest(BooleanFunction.OR, TanHNeuronTest::build);
    }

    @Test
    public void first() {
        NeuronTestHelper.buildTest(BooleanFunction.FIRST, TanHNeuronTest::build);
    }

    @Test
    public void notSecond() {
        NeuronTestHelper.buildTest(BooleanFunction.not(BooleanFunction.SECOND), TanHNeuronTest::build);
    }

    private static Neuron build(final boolean value1, final boolean value2, final boolean value3) {
        return new TanHNeuron(Constant.mapBoolean(value1), Constant.mapBoolean(value2, value3));
    }

    @Test
    public void trainingOutputSize() {
        // GIVEN
        final Neuron neuron = build(true, true, true);

        // WHEN
        final double[] errors = neuron.train(false, true, true);

        // THEN
        Assert.assertEquals(2, errors.length);
    }
}
