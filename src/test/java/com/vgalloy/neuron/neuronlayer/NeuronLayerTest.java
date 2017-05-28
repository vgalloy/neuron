package com.vgalloy.neuron.neuronlayer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vgalloy.neuron.constant.Constant;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Vincent Galloy on 28/05/17.
 *
 * @author Vincent Galloy
 */
public final class NeuronLayerTest {

    @Test
    public void simpleCreation() {
        // GIVEN
        int layerSize = 5;

        // WHEN
        NeuronLayer result = NeuronLayers.of(3, layerSize);

        // THEN
        Assert.assertEquals(layerSize, result.size());
    }

    @Test
    public void correctInputSize() {
        // GIVEN
        int inputSize = 5;
        int layerSize = 6;
        NeuronLayer layer = NeuronLayers.of(inputSize, layerSize);

        // WHEN
        List<Boolean> result = layer.apply(Arrays.asList(true, true, true, true, true));

        // THEN
        Assert.assertEquals(layerSize, result.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongInputSize() {
        // GIVEN
        int inputSize = 5;
        NeuronLayer layer = NeuronLayers.of(inputSize, 5);

        // WHEN
        layer.apply(Arrays.asList(true, true, true, true));

        // THEN
        Assert.fail("exception should occurred");
    }

    @Test
    public void identityLayer() {
        // GIVEN
        NeuronLayer layer = NeuronLayers.of(5, 5);

        // WHEN
        for (int i = 0; i < 1_000; i++) {
            List<Boolean> input = Stream.generate(Constant::random).limit(5).collect(Collectors.toList());
            layer.trainWithBoolean(input, input);
        }

        // THEN
        for (int i = 0; i < 100; i++) {
            List<Boolean> input = Stream.generate(Constant::random).limit(5).collect(Collectors.toList());
            Assert.assertEquals(input, layer.apply(input));
        }
    }
}