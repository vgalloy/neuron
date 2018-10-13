package com.vgalloy.neuron.neuronsystem;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Warmup;

import com.vgalloy.neuron.util.IntFunction;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public class PerformanceTest {

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    /**
     *   47.400 ±(99.9%) 0.388 ops/s [Average]
     *   (min, avg, max) = (45.764, 47.400, 48.336), stdev = 0.690
     *   CI (99.9%): [47.012, 47.788] (assumes normal distribution)
     */
    @Warmup(iterations = 3)
    @Fork(value = 2)
    @Benchmark
    public void init() {
        final NeuronSystem neuronSystem = new NeuronSystemBuilder(4, 6)
            .addLayer(4)
            .addLayer(4)
            .addLayer(3)
            .build();

        NeuronSystemTestHelper.train(neuronSystem, IntFunction.ADD, 2, 100);
    }
}
