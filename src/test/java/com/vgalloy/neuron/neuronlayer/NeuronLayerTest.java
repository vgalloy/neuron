package com.vgalloy.neuron.neuronlayer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.vgalloy.neuron.constant.Constant;
import com.vgalloy.neuron.neuron.Neurons;
import com.vgalloy.neuron.neuron.SimpleNeuron;

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
        final NeuronLayer result = NeuronLayers.of(3, layerSize);

        // THEN
        Assert.assertEquals(layerSize, result.neuronNumber());
    }

    @Test
    public void correctInputSize() {
        // GIVEN
        final int inputSize = 5;
        final int layerSize = 6;
        final NeuronLayer layer = NeuronLayers.of(inputSize, layerSize);

        // WHEN
        final List<Boolean> result = layer.apply(Arrays.asList(true, true, true, true, true));

        // THEN
        Assert.assertEquals(layerSize, result.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongInputSize() {
        // GIVEN
        final int inputSize = 5;
        final NeuronLayer layer = NeuronLayers.of(inputSize, 5);

        // WHEN
        layer.apply(Arrays.asList(true, true, true, true));

        // THEN
        Assert.fail("exception should occurred");
    }

    @Test
    public void identityLayer() {
        // GIVEN
        final NeuronLayer layer = NeuronLayers.of(5, 5);

        // WHEN
        for (int i = 0; i < 1_000; i++) {
            final List<Boolean> input = Stream.generate(Constant::random).limit(5).collect(Collectors.toList());
            layer.trainWithBoolean(input, input);
        }

        // THEN
        for (int i = 0; i < 100; i++) {
            final List<Boolean> input = Stream.generate(Constant::random).limit(5).collect(Collectors.toList());
            Assert.assertEquals(input, layer.apply(input));
        }
    }

    @Test
    public void noCorrectionWhenOk() {
        // GIVEN
        final NeuronLayer layer = NeuronLayers.of(Neurons.of(2), Neurons.of(2), Neurons.of(2));
        final List<Boolean> input = Arrays.asList(true, true);
        final List<Boolean> result = layer.apply(input);

        // WHEN
        final List<Double> correction = layer.trainWithBoolean(input, result);

        // THEN
        Assert.assertEquals(2, correction.size());
        Assert.assertEquals(0, correction.get(0), 0.0001);
        Assert.assertEquals(0, correction.get(1), 0.0001);
    }

    @Test
    public void correction() {
        // GIVEN
        final NeuronLayer layer = NeuronLayers.of(
            new SimpleNeuron(0d, Arrays.asList(Constant.TRUE, 0d)),
            new SimpleNeuron(0d, Arrays.asList(Constant.FALSE, 0d)),
            new SimpleNeuron(0d, Arrays.asList(Constant.FALSE, Constant.FALSE))
        );

        // WHEN
        final List<Double> correction = layer.trainWithBoolean(Arrays.asList(true, true), Arrays.asList(false, false, false));

        // THEN
        Assert.assertEquals(2, correction.size());
        Assert.assertEquals(Constant.FALSE - Constant.TRUE, correction.get(0), 0.0001);
        Assert.assertEquals(0., correction.get(1), 0.0001);
    }
}
