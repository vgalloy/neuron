package com.vgalloy.neuron.neuron.builder;

import com.vgalloy.neuron.neuron.AggregationFunction;

/**
 * Created by Vincent Galloy on 19/10/18.
 *
 * @author Vincent Galloy
 */
public interface TypeBuilder {

    LengthBuilder withType(AggregationFunction aggregationFunction);
}
