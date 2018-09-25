package com.vgalloy.neuron.neuronlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vgalloy.neuron.neuron.Neuron;
import com.vgalloy.neuron.util.NeuronAssert;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
class NeuronLayerImpl implements NeuronLayer {

    private final List<Neuron> neurons;

    NeuronLayerImpl(List<Neuron> neurons) {
        this.neurons = Objects.requireNonNull(neurons);
    }

    @Override
    public List<Boolean> apply(List<Boolean> input) {
        return neurons.stream()
            .map(e -> e.apply(input))
            .collect(Collectors.toList());
    }

    @Override
    public List<Double> trainWithDouble(List<Boolean> input, List<Double> expectedResult) {
        NeuronAssert.checkState(neurons.size() == expectedResult.size(), "ExpectedResult size must be equals to neuron layer size.");

        List<List<Double>> coefficientCorrectionList = new ArrayList<>();
        for (int i = 0; i < neurons.size(); i++) {
            final Neuron neuron = neurons.get(i);
            final List<Double> correction = neuron.train(input, 0 < expectedResult.get(i));
            NeuronAssert.checkState(correction.size() == input.size(), "Correction list size should be equals to input list size.");
            coefficientCorrectionList.add(correction);
        }

        final List<Double> result = Stream.generate(() -> 0d)
            .limit(input.size())
            .collect(Collectors.toList());
        NeuronAssert.checkState(result.size() == input.size(), "Result list size should be equals to input list size.");
        for (List<Double> coefficientCorrection : coefficientCorrectionList) {
            for (int i = 0; i < coefficientCorrection.size(); i++) {
                result.set(i, result.get(i) + coefficientCorrection.get(i));
            }
        }
        return result;
    }

    @Override
    public int size() {
        return neurons.size();
    }

    @Override
    public String toString() {
        return "NeuronLayerImpl{" +
            "neurons=" + neurons +
            '}';
    }
}
