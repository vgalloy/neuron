package com.vgalloy.neuron.neuron;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import com.vgalloy.neuron.constant.Constant;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public final class TanHNeuronTest {

    @Test
    public void and() {
        NeuronTestHelper.buildTest((a, b) -> a && b, TanHNeuronTest::build);
    }

    @Test
    public void or() {
        NeuronTestHelper.buildTest((a, b) -> a || b, TanHNeuronTest::build);
    }

    @Test
    public void first() {
        NeuronTestHelper.buildTest((a, b) -> a, TanHNeuronTest::build);
    }

    @Test
    public void notSecond() {
        NeuronTestHelper.buildTest((a, b) -> !b, TanHNeuronTest::build);
    }

    private static Neuron build(final boolean value1, final boolean value2, final boolean value3) {
        return new TanHNeuron(Constant.mapBoolean(value1), Constant.mapBoolean(Arrays.asList(value2, value3)));
    }

    @Test
    public void trainingOutputSize() {
        // GIVEN
        final Neuron neuron = build(true, true, true);

        // WHEN
        final List<Double> errors = neuron.train(Arrays.asList(true, true), false);

        // THEN
        Assert.assertEquals(2, errors.size());
    }
}
