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
final class SimpleNeuron implements Neuron {

	/**
	 * Learning curve must be positive and lower than {@link Constant#ONE}.
	 */
    private static final Long LEARNING_MULTIPLICATOR = Constant.ONE * 2 / 10;

    private final List<Long> coefficients;

    SimpleNeuron(final Long firstCoefficient, final List<Long> coefficients) {
        Objects.requireNonNull(firstCoefficient);
        Objects.requireNonNull(coefficients);
        NeuronAssert.checkState(coefficients.isEmpty(), "Neuron must have at least on entry point");
        this.coefficients = new ArrayList<>();
        this.coefficients.add(firstCoefficient);
        this.coefficients.addAll(coefficients);
    }

    @Override
    public boolean apply(final List<Boolean> input) {
        NeuronAssert.checkState(input.size() != coefficients.size() - 1, "You are train neuron with " + input.size() + " inputs. But this neuron needs " + (coefficients.size() - 1) + ".");
        final NeuronInput neuronInput = NeuronInput.of(input);

        long result = 0L;
        for (int i = 0; i < coefficients.size(); i++) {
            result += compute(neuronInput, i);
        }

        return result > 0;
    }

    @Override
    public List<Long> train(final List<Boolean> input, final boolean expected) {
        Objects.requireNonNull(input, "NeuronInput can not be null");
        NeuronAssert.checkState(input.size() != coefficients.size() - 1, "You are train neuron with " + input.size() + " inputs. But this neuron needs " + (coefficients.size() - 1) + ".");

        final boolean result = apply(input);

        // Correct case
        if (result == expected) {
            return Stream.generate(() -> 0L).limit(coefficients.size() - 1).collect(Collectors.toList());
        }

        // Learning phase
        final NeuronInput neuronInput = NeuronInput.of(input);
        final List<Long> coefficientCorrection = new ArrayList<>();
        for (int i = 0; i < coefficients.size(); i++) {
            if (!neuronInput.get(i)) {
                coefficientCorrection.add(0L);
            } else {
                final long expectedAsLong = expected ? Constant.ONE : Constant.MINUS_ONE;
                final long diff = (expectedAsLong - compute(neuronInput, i))  * LEARNING_MULTIPLICATOR / Constant.ONE;
                coefficients.set(i, Math.max(Constant.MINUS_ONE, Math.min(Constant.ONE, coefficients.get(i) + diff)));
                coefficientCorrection.add(diff);
            }
        }
        return coefficientCorrection.subList(1, coefficientCorrection.size());
    }

    private long compute(final NeuronInput input, final int i) {
        if(input.get(i)) {
            return coefficients.get(i);
        }
        return 0L;
    }

    @Override
    public String toString() {
        return "SimpleNeuron{" +
            "coefficients=" + coefficients +
            '}';
    }
}
