package com.vgalloy.neuron.neuron;

/**
 * Created by Vincent Galloy on 25/09/2018.
 *
 * @author Vincent Galloy
 */
interface TriFunction<R, S, T, U> {

    U apply(R r, S s, T t);
}
