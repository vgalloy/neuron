package com.vgalloy.neuron.neuron;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.vgalloy.neuron.constant.Constant;
import com.vgalloy.neuron.util.NeuronAssert;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
final class TanHNeuron implements Neuron {

	/**
	 * Learning curve must be positive and lower than {@link Constant#ONE}.
	 */
    private static final double LEARNING_MULTIPLICATOR = 2d / 10;

    private final List<Double> coefficients;

    TanHNeuron(final List<Double> coefficients) {
        Objects.requireNonNull(coefficients);
        NeuronAssert.checkState(!coefficients.isEmpty(), "Neuron must have at least on entry point");
        this.coefficients = new ArrayList<>(coefficients);
    }

    @Override
    public boolean apply(final List<Boolean> input) {
        NeuronAssert.checkState(input.size() == coefficients.size(), "You are train neuron with " + input.size() + " inputs. But this neuron needs " + coefficients.size() + ".");

        final double result = compute(input);
        return Math.tanh(result) > 0;
    }

    @Override
    public List<Double> train(final List<Boolean> input, final boolean expected) {
        Objects.requireNonNull(input, "NeuronInput can not be null");
        NeuronAssert.checkState(input.size() == coefficients.size(), "You are train neuron with " + input.size() + " inputs. But this neuron needs " + coefficients.size() + ".");

        final boolean resultBoolean = apply(input);
        return train(input, Constant.mapBoolean(expected) - Constant.mapBoolean(resultBoolean));
    }

    public List<Double> train(final List<Boolean> input, final double diff) {
        Objects.requireNonNull(input, "NeuronInput can not be null");
        NeuronAssert.checkState(input.size() == coefficients.size(), "You are train neuron with " + input.size() + " inputs. But this neuron needs " + coefficients.size() + ".");

        final double result = compute(input);
        final double error = tanhDerivate(result) * diff;

        final List<Double> coefficientCorrection = new ArrayList<>();
        for (int i = 0; i < coefficients.size(); i++) {
            final double errorPerInput = error * coefficients.get(i);
            coefficientCorrection.add(errorPerInput);
            final double newCoefficient = coefficients.get(i) + error * Constant.mapBoolean(input.get(i)) * LEARNING_MULTIPLICATOR;
            coefficients.set(i, newCoefficient);
        }
        return coefficientCorrection;
    }

    private static double tanhDerivate(final double value) {
        final double tanh = Math.tanh(value);
        final double pow = Math.pow(tanh, tanh);
        if (Double.isNaN(pow)) {
            return 1;
        }
        return 1 - pow;
    }

    private double compute(final List<Boolean> input) {
        double result = 0L;
        for (int i = 0; i < coefficients.size(); i++) {
            result += Constant.mapBoolean(input.get(i)) * coefficients.get(i);
        }
        return result;
    }

    @Override
    public String toString() {
        return "TanHNeuron{" +
            "coefficients=" + coefficients +
            '}';
    }
}
