package com.vgalloy.neuron.neuron;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.vgalloy.neuron.constant.Constant;
import com.vgalloy.neuron.util.NeuronAssert;

/**
 * Created by Vincent Galloy on 25/09/2018.
 *
 * @author Vincent Galloy
 */
abstract class AbstractNeuron implements Neuron {

    /**
     * Learning curve must be positive and lower than 1.
     */
    private static final double LEARNING_MULTIPLICATOR = 2d / 10;

    private final double[] coefficients;
    private final AggregationFunction aggregationFunction;

    AbstractNeuron(final Double firstCoefficient, final List<Double> coefficients, final AggregationFunction aggregationFunction) {
        NeuronAssert.checkState(!coefficients.isEmpty(), "Neuron must have at least on entry point");
        this.coefficients = new double[coefficients.size() + 1];
        this.coefficients[0] = firstCoefficient;
        for (int i = 0; i < coefficients.size(); i++) {
            this.coefficients[i + 1] = coefficients.get(i);
        }
        this.aggregationFunction = Objects.requireNonNull(aggregationFunction, "aggregationFunction");
    }

    @Override
    public boolean apply(boolean... input) {
        checkInputSize(input);

        final Double result = compute(input);
        return getAggregationFunction().apply(result) > 0;
    }

    @Override
    public double[] train(boolean expected, boolean... input) {
        checkInputSize(input);
        final boolean result = apply(input);
        final double diff = Constant.mapBoolean(expected) - Constant.mapBoolean(result);
        return train(diff, input);
    }

    @Override
    public double[] train(final double diff, final boolean... input) {
        checkInputSize(input);

        final double result = compute(input);
        final double error = getAggregationFunction().applyDerived(result) * diff;

        final NeuronInput neuronInput = NeuronInput.of(input);
        final double[] coefficientCorrection = new double[neuronInput.size()];
        for (int i = 0; i < neuronInput.size(); i++) {
            final double errorPerInput = error * this.coefficients[i];
            coefficientCorrection[i] = errorPerInput;
            final double newCoefficient = this.coefficients[i] + error * Constant.mapBoolean(neuronInput.get(i)) * LEARNING_MULTIPLICATOR;
            this.coefficients[i] = newCoefficient;
        }
        return Arrays.copyOfRange(coefficientCorrection, 1, coefficientCorrection.length);
    }

    @Override
    public int inputSize() {
        return coefficients.length - 1;
    }

    protected double[] getCoefficients() {
        return coefficients;
    }

    protected AggregationFunction getAggregationFunction() {
        return aggregationFunction;
    }

    private Double compute(final List<Boolean> input) {
        final NeuronInput neuronInput = NeuronInput.of(input);
        double result = 0;
        for (int i = 0; i < this.coefficients.length; i++) {
            result += compute(neuronInput, i);
        }
        return result;
    }

    private Double compute(final boolean[] input) {
        final NeuronInput neuronInput = NeuronInput.of(input);
        double result = 0;
        for (int i = 0; i < this.coefficients.length; i++) {
            result += compute(neuronInput, i);
        }
        return result;
    }

    private double compute(final NeuronInput neuronInput, final int i) {
        return Constant.mapBoolean(neuronInput.get(i)) * this.coefficients[i];
    }

    private void checkInputSize(final boolean[] input) {
        Objects.requireNonNull(input, "NeuronInput can not be null");
        NeuronAssert.checkState(input.length == inputSize(), "You are training neuron with " + input.length + " inputs. But this neuron needs " + inputSize() + ".");
    }
}
