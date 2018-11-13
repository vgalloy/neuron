package com.vgalloy.neuron.neuronlayer;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.vgalloy.neuron.constant.Constant;
import com.vgalloy.neuron.neuron.Neurons;
import com.vgalloy.neuron.neuron.builder.NeuronBuilder;

/**
 * Created by Vincent Galloy on 28/05/17.
 *
 * @author Vincent Galloy
 */
public final class NeuronLayerTest {

    @Test
    public void simpleCreation() {
        // GIVEN
        final int layerSize = 5;

        // WHEN
        final NeuronLayer result = NeuronLayers.of(Neurons.tanh(), 3, layerSize);

        // THEN
        Assert.assertEquals(layerSize, result.neuronNumber());
    }

    @Test
    public void correctInputSize() {
        // GIVEN
        final int inputSize = 5;
        final int layerSize = 6;
        final NeuronLayer layer = NeuronLayers.of(Neurons.tanh(), inputSize, layerSize);

        // WHEN
        final boolean[] result = layer.apply(true, true, true, true, true);

        // THEN
        Assert.assertEquals(layerSize, result.length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongInputSize() {
        // GIVEN
        final int inputSize = 5;
        final NeuronLayer layer = NeuronLayers.of(Neurons.tanh(), inputSize, 5);

        // WHEN
        layer.apply(true, true, true, true);

        // THEN
        Assert.fail("exception should occurred");
    }

    @Test
    public void identityLayer() {
        // GIVEN
        final NeuronLayer layer = NeuronLayers.of(Neurons.tanh(), 5, 5);

        // WHEN
        for (int i = 0; i < 1_000; i++) {
            final boolean[] input = Constant.toBooleanArray(Stream.generate(Constant::random).limit(5).collect(Collectors.toList()));
            layer.trainWithBoolean(input, input);
        }

        // THEN
        for (int i = 0; i < 100; i++) {
            final boolean[] input = Constant.toBooleanArray(Stream.generate(Constant::random).limit(5).collect(Collectors.toList()));
            Assert.assertArrayEquals(input, layer.apply(input));
        }
    }

    @Test
    public void noCorrectionWhenOk() {
        // GIVEN
        final NeuronBuilder neuronBuilder = Neurons.tanh().inputSize(2);
        final NeuronLayer layer = NeuronLayers.of(neuronBuilder.build(), neuronBuilder.build(), neuronBuilder.build());
        final boolean[] input = new boolean[]{true, true};
        final boolean[] result = layer.apply(input);

        // WHEN
        final double[] correction = layer.trainWithBoolean(input, result);

        // THEN
        Assert.assertEquals(2, correction.length);
        Assert.assertEquals(0, correction[0], 0.0001);
        Assert.assertEquals(0, correction[1], 0.0001);
    }

    @Test
    public void correction() {
        // GIVEN
        final NeuronLayer layer = NeuronLayers.of(
            Neurons.linear().withCoefficient(0d, Constant.TRUE, 0d).build(),
            Neurons.linear().withCoefficient(0d, Constant.FALSE, 0d).build(),
            Neurons.linear().withCoefficient(0d, Constant.FALSE, Constant.FALSE).build()
        );

        // WHEN
        final double[] correction = layer.trainWithBoolean(new boolean[]{true, true}, new boolean[]{false, false, false});

        // THEN
        Assert.assertEquals(2, correction.length);
//        Assert.assertEquals(Constant.FALSE - Constant.TRUE, correction[0], 0.0001);
        Assert.assertEquals(0., correction[1], 0.0001);
    }
}
