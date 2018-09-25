package com.vgalloy.neuron.neuron;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.vgalloy.neuron.constant.Constant;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
final class TanHNeuron extends AbstractNeuron<Double> {

    /**
     * Learning curve must be positive and lower than 1.
     */
    private static final double LEARNING_MULTIPLICATOR = 2d / 10;

    TanHNeuron(final double firstCoefficient, final List<Double> coefficients) {
        super(firstCoefficient, coefficients, a -> Math.tanh(a) > 0);
    }

    @Override
    public List<Double> train(final List<Boolean> input, final boolean expected) {
        Objects.requireNonNull(input, "NeuronInput can not be null");
        checkInputSize(input);

        final boolean resultBoolean = apply(input);
        return train(input, Constant.mapBoolean(expected) - Constant.mapBoolean(resultBoolean));
    }

    public List<Double> train(final List<Boolean> input, final double diff) {
        Objects.requireNonNull(input, "NeuronInput can not be null");

        final double result = compute(input);
        final double error = tanhDerivate(result) * diff;

        final List<Double> coefficientCorrection = new ArrayList<>();
        final NeuronInput neuronInput = NeuronInput.of(input);
        for (int i = 0; i < neuronInput.size(); i++) {
            final double errorPerInput = error * getCoefficients().get(i);
            coefficientCorrection.add(errorPerInput);
            final double newCoefficient = getCoefficients().get(i) + error * Constant.mapBoolean(neuronInput.get(i)) * LEARNING_MULTIPLICATOR;
            getCoefficients().set(i, newCoefficient);
        }
        return coefficientCorrection;
    }

    private static double tanhDerivate(final double value) {
        final double tanh = Math.tanh(value);
        final double pow = Math.pow(tanh, 2);
        if (Double.isNaN(pow)) {
            throw new IllegalStateException("Nan unsupported");
        }
        return 1 - pow;
    }

    protected Double compute(final List<Boolean> input) {
        final NeuronInput neuronInput = NeuronInput.of(input);
        double result = 0d;
        for (int i = 0; i < getCoefficients().size(); i++) {
            result += compute(neuronInput, i);
        }
        return result;
    }

    protected double compute(final NeuronInput input, final int i) {
        return Constant.mapBoolean(input.get(i)) * getCoefficients().get(i);
    }

    @Override
    public String toString() {
        return "TanH" + getCoefficients();
    }
}
