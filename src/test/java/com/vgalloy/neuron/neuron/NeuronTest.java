package com.vgalloy.neuron.neuron;

import org.junit.Test;
import java.util.Collections;

/**
 * Created by Vincent Galloy on 26/09/2018.
 *
 * @author Vincent Galloy
 */
public final class NeuronTest {

    @Test(expected = NullPointerException.class)
    public void creationFail() {
        // GIVEN
        Neurons.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creationFail2() {
        // GIVEN
        Neurons.of(Collections.emptyList());
    }
}
