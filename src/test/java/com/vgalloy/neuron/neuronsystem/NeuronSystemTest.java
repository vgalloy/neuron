package com.vgalloy.neuron.neuronsystem;

import java.util.Arrays;
import java.util.function.BiFunction;

import org.junit.Ignore;
import org.junit.Test;

import com.vgalloy.neuron.neuronlayer.NeuronLayers;
import com.vgalloy.neuron.util.BooleanNeuron;

/**
 * Created by Vincent Galloy on 25/09/2018.
 *
 * @author Vincent Galloy
 */
public final class NeuronSystemTest {

    private static final BiFunction<Integer, Integer, Integer> ADD = (a, b) -> a + b;
    private static final BiFunction<Integer, Integer, Integer> ADD_ONE = (a, b) -> a + 1;

    @Test
    public void addOneBit() {
        final NeuronSystem neuronSystem = new NeuronSystemBuilder(2, 3)
            .addLayer(2)
            .build();

        NeuronSystemTestHelper.train(neuronSystem, ADD, 1);
        NeuronSystemTestHelper.validate(neuronSystem, ADD, 1);
    }

    @Test
    public void addOneOneBit() {
        final NeuronSystem neuronSystem = new NeuronSystemBuilder(2, 3)
            .addLayer(2)
            .build();

        NeuronSystemTestHelper.train(neuronSystem, ADD_ONE, 1);
        NeuronSystemTestHelper.validate(neuronSystem, ADD_ONE, 1);
    }

    @Test
    public void addWithPreFillNeuron() {
        final NeuronSystem neuronSystem = new NeuronSystemImpl(Arrays.asList(
            NeuronLayers.of(
                BooleanNeuron.notAnd(4).apply(0, 2),
                BooleanNeuron.or(4).apply(0, 2),
                BooleanNeuron.and(4).apply(0, 2),
                BooleanNeuron.notAnd(4).apply(1, 3),
                BooleanNeuron.or(4).apply(1, 3),
                BooleanNeuron.and(4).apply(1, 3)
            ),
            NeuronLayers.of(
                BooleanNeuron.and(6).apply(0, 1),
                BooleanNeuron.one(6).apply(2),
                BooleanNeuron.and(6).apply(3, 4),
                BooleanNeuron.atLeast(6, 2).apply(2, 4, 5)
            ),
            NeuronLayers.of(
                BooleanNeuron.one(4).apply(0),
                BooleanNeuron.notAnd(4).apply(1, 2),
                BooleanNeuron.or(4).apply(1, 2),
                BooleanNeuron.one(4).apply(3)
            ),
            NeuronLayers.of(
                BooleanNeuron.one(4).apply(0),
                BooleanNeuron.and(4).apply(1, 2),
                BooleanNeuron.one(4).apply(3)
            )
        ));
        NeuronSystemTestHelper.validate(neuronSystem, ADD, 2);
    }

    @Test
    @Ignore
    public void add() {
        NeuronSystemTestHelper.test(ADD);
    }
}
