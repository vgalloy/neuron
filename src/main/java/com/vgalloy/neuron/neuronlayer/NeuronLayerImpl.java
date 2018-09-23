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
    public List<Long> trainWithLong(List<Boolean> input, List<Long> expectedResult) {
        NeuronAssert.checkState(neurons.size() == expectedResult.size(), "ExpectedResult size must be equals to neuron layer size.");

        List<List<Long>> coefficientCorrectionList = new ArrayList<>();
        for (int i = 0; i < neurons.size(); i++) {
            Neuron neuron = neurons.get(i);
            List<Long> correction = neuron.train(input, expectedResult.get(i) > 0);
            NeuronAssert.checkState(correction.size() == input.size(), "Correction list size should be equals to input list size.");
            coefficientCorrectionList.add(correction);
        }

        List<Long> result = Stream.generate(() -> 0L)
            .limit(input.size())
            .collect(Collectors.toList());
        NeuronAssert.checkState(result.size() == input.size(), "Result list size should be equals to input list size.");
        for (List<Long> bigDecimals : coefficientCorrectionList) {
            for (int i = 0; i < bigDecimals.size(); i++) {
                result.set(i, result.get(i) + bigDecimals.get(i));
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
