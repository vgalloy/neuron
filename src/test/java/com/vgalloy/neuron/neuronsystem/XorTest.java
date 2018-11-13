package com.vgalloy.neuron.neuronsystem;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.vgalloy.neuron.constant.Constant;
import com.vgalloy.neuron.neuron.Neurons;
import com.vgalloy.neuron.neuronlayer.NeuronLayers;
import com.vgalloy.neuron.util.BooleanFunction;
import com.vgalloy.neuron.util.BooleanNeuron;

/**
 * Created by Vincent Galloy on 22/04/17.
 *
 * @author Vincent Galloy
 */
public final class XorTest {

    @Test
    public void testSimple() {
        // GIVEN
        final NeuronSystem neuronSystem = new NeuronSystemBuilder(Neurons.tanh(), 2, 3)
            .addLayer(2)
            .addLayer(1)
            .build();

        // WHEN
        train(neuronSystem);

        // THEN
        validate(neuronSystem);
    }

    @Test
    public void xor() {
        // GIVEN
        final NeuronSystem neuronSystem = new NeuronSystemBuilder(Neurons.tanh(), 2, 2)
            .addLayer(1)
            .build();

        // WHEN
        train(neuronSystem);

        // THEN
        validate(neuronSystem);
    }

    @Test
    public void xorWithPreFilledNeuron() {
        final NeuronSystem neuronSystem = new NeuronSystemImpl(
            NeuronLayers.of(BooleanNeuron.NOT_AND, BooleanNeuron.OR),
            NeuronLayers.of(BooleanNeuron.AND)
        );

        validate(neuronSystem);
    }

    private static void train(final NeuronSystem neuronSystem) {
        final List<List<Boolean>> training = Arrays.asList(
            Arrays.asList(Boolean.TRUE, Boolean.TRUE),
            Arrays.asList(Boolean.TRUE, Boolean.FALSE),
            Arrays.asList(Boolean.FALSE, Boolean.TRUE),
            Arrays.asList(Boolean.FALSE, Boolean.FALSE)
        );
        for (int i = 0; i < 10_000; i++) {
            Collections.shuffle(training);
            training.forEach(train -> neuronSystem.trainWithBoolean(Constant.toBooleanArray(train), BooleanFunction.XOR.apply(train.get(0), train.get(1))));
        }
    }

    private static void validate(final NeuronSystem neuronSystem) {
        validate(neuronSystem, true, true);
        validate(neuronSystem, true, false);
        validate(neuronSystem, false, true);
        validate(neuronSystem, false, false);
    }

    private static void validate(final NeuronSystem neuronSystem, final boolean a, final boolean b) {
        Assert.assertEquals(BooleanFunction.XOR.apply(a, b), neuronSystem.apply(a, b)[0]);
    }
}
