package com.vgalloy.neuron.neuronsystem;

import java.util.Arrays;
import java.util.function.BiFunction;

import org.junit.Ignore;
import org.junit.Test;

import com.vgalloy.neuron.neuron.Neurons;
import com.vgalloy.neuron.neuronlayer.NeuronLayers;

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
                Neurons.of(2d, -3d, 0d, -3d, 0d),   // NAND  (0,2)
                Neurons.of(1d, 2d, 0d, 2d, 0d),     // OR    (0,2)
                Neurons.of(-3d, 2d, 0d, 2d, 0d),    // AND   (0,2)
                Neurons.of(2d, 0d, -3d, 0d, -3d),   // NAND  (1,3)
                Neurons.of(1d, 0d, 2d, 0d, 2d),     // OR    (1,3)
                Neurons.of(-3d, 0d, 2d, 0d, 2d)     // AND   (1,3)
            ),
            NeuronLayers.of(
                Neurons.of(-3d, 2d, 2d, 0d, 0d, 0d, 0d),
                Neurons.of(0d, 0d, 0d, 1d, 0d, 0d, 0d),
                Neurons.of(-3d, 0d, 0d, 0d, 2d, 2d, 0d),
                Neurons.of(2d, 0d, 0d, 3d, 0d, 3d, 3d)
            ),
            NeuronLayers.of(
                Neurons.of(0d, 1d, 0d, 0d, 0d),
                Neurons.of(2d, 0d, -3d, -3d, 0d),
                Neurons.of(1d, 0d, 2d, 2d, 0d),
                Neurons.of(0d, 0d, 0d, 0d, 1d)
            ),
            NeuronLayers.of(
                Neurons.of(0d, 1d, 0d, 0d, 0d),
                Neurons.of(-3d, 0d, 2d, 2d, 0d),
                Neurons.of(0d, 0d, 0d, 0d, 1d)
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
