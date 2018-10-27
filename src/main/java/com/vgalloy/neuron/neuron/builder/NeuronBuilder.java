package com.vgalloy.neuron.neuron.builder;

import com.vgalloy.neuron.neuron.Neuron;

/**
 * Created by Vincent Galloy on 19/10/18.
 *
 * @author Vincent Galloy
 */
public interface NeuronBuilder {

    Neuron build();

    Neuron build(final boolean isImmutable);
}
