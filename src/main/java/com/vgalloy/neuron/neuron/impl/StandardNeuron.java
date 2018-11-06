package com.vgalloy.neuron.neuron.impl;

import java.util.Arrays;
import java.util.Objects;

import com.vgalloy.neuron.constant.Constant;
import com.vgalloy.neuron.neuron.AggregationFunction;
import com.vgalloy.neuron.neuron.Neuron;
import com.vgalloy.neuron.util.NeuronAssert;

/**
 * Created by Vincent Galloy on 25/09/2018.
 *
 * @author Vincent Galloy
 */
public class StandardNeuron implements Neuron {

    /**
     * Learning curve must be positive and lower than 1.
     */
    private static final double LEARNING_MULTIPLICATOR = 2d / 10;

    protected double firstCoefficient;
    protected final double[] coefficients;
    private final AggregationFunction aggregationFunction;

    public StandardNeuron(final double firstCoefficient, final AggregationFunction aggregationFunction, final double... coefficients) {
        NeuronAssert.state(coefficients.length != 0, "Neuron must have at least on entry point");
        this.firstCoefficient = firstCoefficient;
        this.coefficients = Arrays.copyOf(coefficients, coefficients.length);
        this.aggregationFunction = Objects.requireNonNull(aggregationFunction, "aggregationFunction");
    }

    @Override
    public double apply(final double... input) {
       throw new UnsupportedOperationException();
    }

    @Override
    public boolean applyBoolean(boolean... input) {
        checkInputSize(input);

        final double result = compute(Constant.toDoubleArray(input));
        return aggregationFunction.apply(result) > 0;
    }

    @Override
    public double[] train(boolean expected, boolean... input) {
        checkInputSize(input);

        final boolean result = applyBoolean(input);
        final double diff = Constant.toDoubleList(expected) - Constant.toDoubleList(result);
        return train(diff, input);
    }

    @Override
    public double[] train(final double diff, final boolean... input) {
        checkInputSize(input);

        final double result = compute(Constant.toDoubleArray(input));
        final double error = aggregationFunction.applyDerived(result) * diff;

        this.firstCoefficient = computeError(firstCoefficient, true, error).newCoefficient;
        final double[] coefficientCorrection = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            final ErrorOutput errorOutput = computeError(this.coefficients[i], input[i], error);
            coefficientCorrection[i] = errorOutput.errorPerInput;
            this.coefficients[i] = errorOutput.newCoefficient;
        }
        return coefficientCorrection;
    }

    @Override
    public int inputSize() {
        return coefficients.length;
    }

    private double compute(final double[] input) {
        double result = firstCoefficient;
        for (int i = 0; i < input.length; i++) {
            result += input[i] * this.coefficients[i];
        }
        return result;
    }

    protected ErrorOutput computeError(final double currentCoefficient, final boolean input, final double error) {
        final double errorPerInput = error * currentCoefficient;
        final double newCoefficient = currentCoefficient + error * Constant.toDoubleList(input) * LEARNING_MULTIPLICATOR;
        return new ErrorOutput(errorPerInput, newCoefficient);
    }

    private void checkInputSize(final boolean[] input) {
        Objects.requireNonNull(input, "NeuronInput can not be null");
        NeuronAssert.state(input.length == inputSize(), "You are training neuron with " + input.length + " inputs. But this neuron needs " + inputSize() + ".");
    }

    protected static class ErrorOutput {
        private final double errorPerInput;
        private final double newCoefficient;

        protected ErrorOutput(final double errorPerInput, final double newCoefficient) {
            this.errorPerInput = errorPerInput;
            this.newCoefficient = newCoefficient;
        }
    }

    @Override
    public String toString() {
        return "N[" + firstCoefficient + Arrays.toString(coefficients) + "]";
    }
}
