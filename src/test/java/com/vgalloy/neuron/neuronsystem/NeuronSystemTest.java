package com.vgalloy.neuron.neuronsystem;

import org.junit.Ignore;
import org.junit.Test;

import com.vgalloy.neuron.neuron.Neurons;
import com.vgalloy.neuron.neuronlayer.NeuronLayers;
import com.vgalloy.neuron.util.BooleanNeuron;
import com.vgalloy.neuron.util.IntFunction;

/**
 * Created by Vincent Galloy on 25/09/2018.
 *
 * @author Vincent Galloy
 */
public final class NeuronSystemTest {

    @Test
    public void addOneBit() {
        final NeuronSystem neuronSystem = new NeuronSystemBuilder(2, 3)
            .addLayer(2)
            .build();

        NeuronSystemTestHelper.train(neuronSystem, IntFunction.ADD, 1);
        NeuronSystemTestHelper.validate(neuronSystem, IntFunction.ADD, 1);
    }

    @Test
    public void addOneOneBit() {
        final NeuronSystem neuronSystem = new NeuronSystemBuilder(2, 3)
            .addLayer(2)
            .build();

        NeuronSystemTestHelper.train(neuronSystem, IntFunction.ADD_ONE, 1);
        NeuronSystemTestHelper.validate(neuronSystem, IntFunction.ADD_ONE, 1);
    }

    @Test
    public void addWithPreFillNeuron() {
        final NeuronSystem neuronSystem = new NeuronSystemImpl(
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
        );
        NeuronSystemTestHelper.validate(neuronSystem, IntFunction.ADD, 2);
    }

    @Test
    @Ignore
    public void add() {
        final NeuronSystem neuronSystem = new NeuronSystemImpl(
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
                Neurons.tanh().inputSize(4).build(),
                Neurons.tanh().inputSize(4).build(),
                Neurons.tanh().inputSize(4).build()
            )
        );

        NeuronSystemTestHelper.train(neuronSystem, IntFunction.ADD, 2);
        NeuronSystemTestHelper.validate(neuronSystem, IntFunction.ADD, 2);
    }
}
