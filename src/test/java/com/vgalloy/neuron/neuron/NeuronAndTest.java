package com.vgalloy.neuron.neuron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.vgalloy.neuron.constant.Constant;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Vincent Galloy on 10/05/17.
 *
 * @author Vincent Galloy
 */
public final class NeuronAndTest {

    @Test
    public void andTrueTrueTrue() {
        // GIVEN
        final Neuron neuron = build(true, true, true);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void andTrueTrueFalse() {
        // GIVEN
        final Neuron neuron = build(true, true, false);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void andTrueFalseTrue() {
        // GIVEN
        final Neuron neuron = build(true, false, true);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void andTrueFalseFalse() {
        // GIVEN
        final Neuron neuron = build(true, false, false);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void andFalseTrueTrue() {
        // GIVEN
        final Neuron neuron = build(false, true, true);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void andFalseTrueFalse() {
        // GIVEN
        final Neuron neuron = build(false, true, false);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void andFalseFalseTrue() {
        // GIVEN
        final Neuron neuron = build(false, false, true);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void andFalseFalseFalse() {
        // GIVEN
        final Neuron neuron = build(false, false, false);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    private Neuron build(boolean value1, boolean value2, boolean value3) {
        return Neurons.of(Constant.map(value1), Constant.map(Arrays.asList(value2, value3)));
    }

    private void train(final Neuron neuron) {
        final List<List<Boolean>> training = new ArrayList<>();
        training.add(Arrays.asList(true, true));
        training.add(Arrays.asList(true, false));
        training.add(Arrays.asList(false, true));
        training.add(Arrays.asList(false, false));
        for (int i = 0; i < 50; i++) {
            Collections.shuffle(training);
            training.forEach(e -> neuron.train(e, e.get(0) && e.get(1)));
        }
    }

    private void validate(final Neuron neuron) {
        Assert.assertTrue(neuron.apply(Arrays.asList(true, true)));
        Assert.assertFalse(neuron.apply(Arrays.asList(false, true)));
        Assert.assertFalse(neuron.apply(Arrays.asList(true, false)));
        Assert.assertFalse(neuron.apply(Arrays.asList(false, false)));
    }
}
