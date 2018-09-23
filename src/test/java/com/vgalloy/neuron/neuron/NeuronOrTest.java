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
public final class NeuronOrTest {

    @Test
    public void orTrueTrueTrue() {
        // GIVEN
        final Neuron neuron = build(true, true, true);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void orTrueTrueFalse() {
        // GIVEN
        final Neuron neuron = build(true, true, false);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void orTrueFalseTrue() {
        // GIVEN
        final Neuron neuron = build(true, false, true);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void orTrueFalseFalse() {
        // GIVEN
        final Neuron neuron = build(true, false, false);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void orFalseTrueTrue() {
        // GIVEN
        final Neuron neuron = build(false, true, true);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void orFalseTrueFalse() {
        // GIVEN
        final Neuron neuron = build(false, true, false);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void orFalseFalseTrue() {
        // GIVEN
        final Neuron neuron = build(false, false, true);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void orFalseFalseFalse() {
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
            neuron.train(Arrays.asList(false, true), true);
            neuron.train(Arrays.asList(true, false), true);
            neuron.train(Arrays.asList(false, false), false);
        }
    }

    private void validate(final Neuron neuron) {
        Assert.assertTrue(neuron.apply(Arrays.asList(true, true)));
        Assert.assertTrue(neuron.apply(Arrays.asList(false, true)));
        Assert.assertTrue(neuron.apply(Arrays.asList(true, false)));
        Assert.assertFalse(neuron.apply(Arrays.asList(false, false)));
    }
}
