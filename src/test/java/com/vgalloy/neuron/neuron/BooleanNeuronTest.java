package com.vgalloy.neuron.neuron;

import org.junit.Test;

/**
 * Created by Vincent Galloy on 26/09/2018.
 *
 * @author Vincent Galloy
 */
public final class BooleanNeuronTest {

    @Test
    public void and() {
        NeuronTestHelper.validate(BooleanNeuron.AND, (a, b) -> a && b);
    }

    @Test
    public void or() {
        NeuronTestHelper.validate(BooleanNeuron.OR, (a, b) -> a || b);
    }

    @Test
    public void first() {
        NeuronTestHelper.validate(BooleanNeuron.FIRST, (a, b) -> a);
    }

    @Test
    public void second() {
        NeuronTestHelper.validate(BooleanNeuron.SECOND, (a, b) -> b);
    }

    @Test
    public void maxOne() {
        NeuronTestHelper.validate(BooleanNeuron.MAX_ONE, (a, b) -> !(a && b));
    }
}
