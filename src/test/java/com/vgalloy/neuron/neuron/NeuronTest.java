package com.vgalloy.neuron.neuron;

import java.util.Collections;
import java.util.List;

import com.vgalloy.neuron.constant.Constant;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
public final class NeuronTest {

    @Test
    public void correctMonoNeuron() {
        // GIVEN
        Neuron neuron = Neurons.of(Collections.singletonList(1L));

        // WHEN
        Boolean result = neuron.apply(Collections.singletonList(true));

        // THEN
        Assert.assertFalse(result);
    }

    @Test
    public void correctMonoNeuron2() {
        // GIVEN
        Neuron neuron = Neurons.of(Collections.singletonList(2L));

        // WHEN
        Boolean result = neuron.apply(Collections.singletonList(true));

        // THEN
        Assert.assertTrue(result);
    }

    @Test
    public void neuronTrain() {
        // GIVEN
        Neuron neuron = Neurons.of(Collections.singletonList(2L));

        // WHEN
        neuron.train(Collections.singletonList(true), false);
        neuron.train(Collections.singletonList(true), false);
        Boolean result = neuron.apply(Collections.singletonList(true));

        // THEN
        Assert.assertFalse(result);
    }

    @Test
    public void neuronTrainResult() {
        // GIVEN
        Neuron neuron = Neurons.of(Collections.singletonList(2L));

        // WHEN
        List<Long> result = neuron.train(Collections.singletonList(true), false);

        // THEN
        Assert.assertEquals(1, result.size());
        for (int i = 0; i < result.size(); i++) {
            Assert.assertEquals(6 * Constant.MINUS_ONE / 10, (long) result.get(i));
        }
    }

    @Test
    public void noNamedTest() {
        // GIVEN
        Neuron neuron = Neurons.of(Collections.singletonList(2L));

        // WHEN
        neuron.train(Collections.singletonList(true), false);
        neuron.train(Collections.singletonList(true), false);
        neuron.train(Collections.singletonList(true), true);
        Boolean result = neuron.apply(Collections.singletonList(true));

        // THEN
        Assert.assertTrue(result);
    }

    @Test(expected = NullPointerException.class)
    public void creationFail() {
        // GIVEN
        Neurons.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creationFail2() {
        // GIVEN
        Neurons.of(Collections.emptyList());
    }
}
