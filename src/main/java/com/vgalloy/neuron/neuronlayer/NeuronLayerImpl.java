package com.vgalloy.neuron.neuronlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vgalloy.neuron.constant.Constant;
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
        NeuronAssert.checkState(!neurons.isEmpty(), "Layer must contains at least one neuron");
        this.neurons = Objects.requireNonNull(neurons);
    }

    @Override
    public List<Boolean> apply(final List<Boolean> input) {
        return neurons.stream()
            .map(e -> e.apply(Constant.toArray(input)))
            .collect(Collectors.toList());
    }

    @Override
    public List<Double> trainWithDouble(final List<Boolean> input, final List<Double> error) {
        NeuronAssert.checkState(neurons.size() == error.size(), "Error vector size must be equals to neuron layer size.");

        List<List<Double>> coefficientCorrectionList = new ArrayList<>();
        for (int i = 0; i < neurons.size(); i++) {
            final Neuron neuron = neurons.get(i);
            final List<Double> correction = Constant.toList(neuron.train(error.get(i), Constant.toArray(input)));
            NeuronAssert.checkState(correction.size() == input.size(), "Correction list size should be equals to input list size.");
            coefficientCorrectionList.add(correction);
        }

        final List<Double> result = Stream.generate(() -> 0d)
            .limit(input.size())
            .collect(Collectors.toList());
        NeuronAssert.checkState(result.size() == input.size(), "Result list size should be equals to input list size.");
        for (final List<Double> coefficientCorrection : coefficientCorrectionList) {
            for (int i = 0; i < coefficientCorrection.size(); i++) {
                result.set(i, result.get(i) + coefficientCorrection.get(i));
            }
        }
        return result;
    }

    @Override
    public List<Double> trainWithBoolean(List<Boolean> input, List<Boolean> expectedSolution) {
        final List<Boolean> result = apply(input);
        final List<Double> diff = new ArrayList<>();
        for (int i = 0; i < expectedSolution.size(); i++) {
            diff.add(Constant.mapBoolean(expectedSolution.get(i)) - Constant.mapBoolean(result.get(i)));
        }
        return trainWithDouble(input, diff);
    }

    @Override
    public int inputSize() {
        return neurons.get(0).inputSize();
    }

    @Override
    public int neuronNumber() {
        return neurons.size();
    }

    @Override
    public String toString() {
        return "Layer" + neurons;
    }
}
