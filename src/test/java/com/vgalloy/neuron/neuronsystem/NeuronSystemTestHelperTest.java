package com.vgalloy.neuron.neuronsystem;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Vincent Galloy on 25/09/2018.
 *
 * @author Vincent Galloy
 */
public final class NeuronSystemTestHelperTest {

    @Test
    public void toBoolean3() {
        // WHEN
        final List<Boolean> booleans = NeuronSystemTestHelper.toBoolean(3, 2);

        // THEN
        Assert.assertEquals(2, booleans.size());
        Assert.assertTrue(booleans.get(0));
        Assert.assertTrue(booleans.get(1));
    }

    @Test
    public void toBoolean1() {
        // WHEN
        final List<Boolean> booleans = NeuronSystemTestHelper.toBoolean(1, 2);

        // THEN
        Assert.assertEquals(2, booleans.size());
        Assert.assertTrue(booleans.get(0));
        Assert.assertFalse(booleans.get(1));
    }

    @Test
    public void reverse3() {
        // GIVEN
        final int value = 3;

        // WHEN
        final int result = NeuronSystemTestHelper.toInt(NeuronSystemTestHelper.toBoolean(value, 2));

        // THEN
        Assert.assertEquals(value, result);
    }

    @Test
    public void reverseTrueFalse() {
        // GIVEN
        final List<Boolean> two = Arrays.asList(false, true);

        // WHEN
        final List<Boolean> result = NeuronSystemTestHelper.toBoolean(NeuronSystemTestHelper.toInt(two), 2);

        // THEN
        Assert.assertEquals(two, result);
    }
}
