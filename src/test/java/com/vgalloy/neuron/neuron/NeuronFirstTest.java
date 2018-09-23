package com.vgalloy.neuron.neuron;

import java.util.Arrays;

import com.vgalloy.neuron.constant.Constant;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Vincent Galloy on 10/05/17.
 *
 * @author Vincent Galloy
 */
public final class NeuronFirstTest {

    @Test
    public void firstTrueTrueTrue() {
        // GIVEN
        final Neuron neuron = build(true, true, true);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void firstTrueTrueFalse() {
        // GIVEN
        final Neuron neuron = build(true, true, false);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void firstTrueFalseTrue() {
        // GIVEN
        final Neuron neuron = build(true, false, true);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void firstTrueFalseFalse() {
        // GIVEN
        final Neuron neuron = build(true, false, false);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void firstFalseTrueTrue() {
        // GIVEN
        final Neuron neuron = build(false, true, true);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void firstFalseTrueFalse() {
        // GIVEN
        final Neuron neuron = build(false, true, false);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void firstFalseFalseTrue() {
        // GIVEN
        final Neuron neuron = build(false, false, true);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void firstFalseFalseFalse() {
        // GIVEN
        final Neuron neuron = build(false, false, false);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    private Neuron build(final boolean value1, final boolean value2, final boolean value3) {
        return Neurons.of(Constant.map(value1), Constant.map(Arrays.asList(value2, value3)));
    }

    private void train(final Neuron neuron) {
        for (int i = 0; i < 30; i++) {
            neuron.train(Arrays.asList(true, true), true);
            neuron.train(Arrays.asList(false, true), false);
            neuron.train(Arrays.asList(true, false), true);
            neuron.train(Arrays.asList(false, false), false);
        }
    }

    private void validate(final Neuron neuron) {
        Assert.assertTrue(neuron.apply(Arrays.asList(true, true)));
        Assert.assertFalse(neuron.apply(Arrays.asList(false, true)));
        Assert.assertTrue(neuron.apply(Arrays.asList(true, false)));
        Assert.assertFalse(neuron.apply(Arrays.asList(false, false)));
    }
}
