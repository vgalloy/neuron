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
public final class NeuronAndTest {

    @Test
    public void andTrueTrueTrue() {
        // GIVEN
        Neuron neuron = build(true, true, true);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void andTrueTrueFalse() {
        // GIVEN
        Neuron neuron = build(true, true, false);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void andTrueFalseTrue() {
        // GIVEN
        Neuron neuron = build(true, false, true);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void andTrueFalseFalse() {
        // GIVEN
        Neuron neuron = build(true, false, false);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void andFalseTrueTrue() {
        // GIVEN
        Neuron neuron = build(false, true, true);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void andFalseTrueFalse() {
        // GIVEN
        Neuron neuron = build(false, true, false);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void andFalseFalseTrue() {
        // GIVEN
        Neuron neuron = build(false, false, true);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    @Test
    public void andFalseFalseFalse() {
        // GIVEN
        Neuron neuron = build(false, false, false);

        // WHEN
        train(neuron);

        // THEN
        validate(neuron);
    }

    private Neuron build(boolean value1, boolean value2, boolean value3) {
        return Neurons.of(Constant.map(value1), Constant.map(Arrays.asList(value2, value3)));
    }

    private void train(Neuron neuron) {
        for (int i = 0; i < 30; i++) {
            neuron.train(Arrays.asList(true, true), true);
            System.out.println(neuron);
            neuron.train(Arrays.asList(false, true), false);
            System.out.println(neuron);
            neuron.train(Arrays.asList(true, false), false);
            System.out.println(neuron);
            neuron.train(Arrays.asList(false, false), false);
            System.out.println(neuron);
        }
    }

    private void validate(Neuron neuron) {
        Assert.assertTrue(neuron.apply(Arrays.asList(true, true)));
        Assert.assertFalse(neuron.apply(Arrays.asList(false, true)));
        Assert.assertFalse(neuron.apply(Arrays.asList(true, false)));
        Assert.assertFalse(neuron.apply(Arrays.asList(false, false)));
    }
}
