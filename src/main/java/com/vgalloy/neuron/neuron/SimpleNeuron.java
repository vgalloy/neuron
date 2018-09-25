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
final class SimpleNeuron extends AbstractNeuron<Long> {

    /**
     * Learning curve must be positive and lower than {@link Constant#ONE}.
     */
    private static final Long LEARNING_MULTIPLICATOR = Constant.ONE * 2 / 10;

    SimpleNeuron(final long firstCoefficient, final List<Long> coefficients) {
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
                final long expectedAsLong = expected ? Constant.ONE : Constant.MINUS_ONE;
                final long error = (expectedAsLong - compute(neuronInput, i));
                final long newCoeff = getCoefficients().get(i) + error * LEARNING_MULTIPLICATOR / Constant.ONE;
                checkCoefficient(newCoeff);
                coefficientCorrection.add((double) error * getCoefficients().get(i) / Constant.ONE);
                getCoefficients().set(i, newCoeff);
            }
        }
        return coefficientCorrection.subList(1, coefficientCorrection.size());
    }

    protected Long compute(final List<Boolean> input) {
        final NeuronInput neuronInput = NeuronInput.of(input);
        long result = 0;
        for (int i = 0; i < getCoefficients().size(); i++) {
            result += compute(neuronInput, i);
        }
        return result;
    }

    protected long compute(final NeuronInput input, final int i) {
        return Constant.map(input.get(i)) * getCoefficients().get(i) / Constant.ONE;
    }

    private static void checkCoefficient(final long coefficient) {
        NeuronAssert.checkState(coefficient <= Constant.ONE, "New coefficient is higher than 'ONE' : " + coefficient);
        NeuronAssert.checkState(Constant.MINUS_ONE <= coefficient, "New coefficient is lower than 'MINUS_ONE' : " + coefficient);
    }

    @Override
    public String toString() {
        return "Simple" + getCoefficients();
    }
}
