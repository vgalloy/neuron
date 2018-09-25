package com.vgalloy.neuron.neuron;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vgalloy.neuron.constant.Constant;
import com.vgalloy.neuron.util.NeuronAssert;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
final class SimpleNeuron extends AbstractNeuron<Double> {

    /**
     * Learning curve must be positive and lower than 1.
     */
    private static final double LEARNING_MULTIPLICATOR =  2d / 10;

    SimpleNeuron(final Double firstCoefficient, final List<Double> coefficients) {
        super(firstCoefficient, coefficients, a -> a > 0);
    }

    @Override
    public List<Double> train(final List<Boolean> input, final boolean expected) {
        Objects.requireNonNull(input, "NeuronInput can not be null");
        checkInputSize(input);

        final boolean result = apply(input);

        // Correct case
        if (result == expected) {
            return Stream.generate(() -> 0d)
                .limit(inputSize())
                .collect(Collectors.toList());
        }

        // Learning phase
        final NeuronInput neuronInput = NeuronInput.of(input);
        final List<Double> coefficientCorrection = new ArrayList<>();
        for (int i = 0; i < getCoefficients().size(); i++) {
            if (!neuronInput.get(i)) {
                coefficientCorrection.add(0d);
            } else {
                final double expectedAsLong = expected ? 1d : -1d;
                final double error = (expectedAsLong - compute(neuronInput, i));
                final double newCoeff = getCoefficients().get(i) + error * LEARNING_MULTIPLICATOR;
                checkCoefficient(newCoeff);
                coefficientCorrection.add(error * getCoefficients().get(i));
                getCoefficients().set(i, newCoeff);
            }
        }
        return coefficientCorrection.subList(1, coefficientCorrection.size());
    }

    protected Double compute(final List<Boolean> input) {
        final NeuronInput neuronInput = NeuronInput.of(input);
        double result = 0;
        for (int i = 0; i < getCoefficients().size(); i++) {
            result += compute(neuronInput, i);
        }
        return result;
    }

    protected double compute(final NeuronInput input, final int i) {
        return Constant.mapBoolean(input.get(i)) * getCoefficients().get(i);
    }

    private static void checkCoefficient(final double coefficient) {
        NeuronAssert.checkState(coefficient <= 1, "New coefficient is higher than 1 : " + coefficient);
        NeuronAssert.checkState(-1 <= coefficient, "New coefficient is lower than -1 : " + coefficient);
    }

    @Override
    public String toString() {
        return "Simple" + getCoefficients();
    }
}
