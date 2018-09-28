package com.vgalloy.neuron.neuron;

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
}
