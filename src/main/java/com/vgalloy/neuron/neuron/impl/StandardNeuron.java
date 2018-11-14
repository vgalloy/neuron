package com.vgalloy.neuron.neuron.impl;

import java.util.Arrays;
import java.util.Objects;

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
    public double apply(final boolean... input) {
        return apply(aggregationFunction.toDoubleArray(input));
    }

    @Override
    public double apply(final double... input) {
        checkInput(input);

        final double result = compute(input);
        return aggregationFunction.apply(result);
    }

    @Override
    public boolean applyBoolean(final boolean... input) {
        checkInput(input);

        final double result = compute(aggregationFunction.toDoubleArray(input));
        return aggregationFunction.activation(aggregationFunction.apply(result));
    }

    @Override
    public double[] train(final boolean expected, final boolean... input) {
        checkInput(input);

        final double result = apply(input);
        final double diff = aggregationFunction.toDouble(expected) - result;
        return train(diff, aggregationFunction.toDoubleArray(input));
    }

    @Override
    public double[] train(final double diff, final double... input) {
        checkInput(input);

        final double result = compute(input);
        final double derived = aggregationFunction.applyDerived(result);
        final double error = derived * diff;

        this.firstCoefficient += computeError(firstCoefficient, aggregationFunction.trueValue(), error).correction;
        final double[] coefficientCorrection = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            final ErrorOutput errorOutput = computeError(this.coefficients[i], input[i], error);
            coefficientCorrection[i] = errorOutput.errorPerInput;
            this.coefficients[i] += errorOutput.correction;
        }
        return coefficientCorrection;
    }

    @Override
    public int inputSize() {
        return coefficients.length;
    }

    @Override
    public AggregationFunction function() {
        return this.aggregationFunction;
    }

    private double compute(final double[] input) {
        double result = firstCoefficient;
        for (int i = 0; i < input.length; i++) {
            result += input[i] * this.coefficients[i];
        }
        return result;
    }

    protected ErrorOutput computeError(final double currentCoefficient, final double input, final double error) {
        final double errorPerInput = error * currentCoefficient;
        final double correction = error * input * LEARNING_MULTIPLICATOR;
        return new ErrorOutput(errorPerInput, correction);
    }

    private void checkInput(final boolean[] inputs) {
        Objects.requireNonNull(inputs, "NeuronInput can not be null");
        NeuronAssert.state(inputs.length == inputSize(), "You are training neuron with " + inputs.length + " inputs. But this neuron needs " + inputSize() + ".");
    }

    private void checkInput(final double[] inputs) {
        Objects.requireNonNull(inputs, "NeuronInput can not be null");
        NeuronAssert.state(inputs.length == inputSize(), "You are training neuron with " + inputs.length + " inputs. But this neuron needs " + inputSize() + ".");
        for (final double input : inputs) {
            NeuronAssert.state(this.aggregationFunction.falseValue() <= input, "Input : " + input + " is lower than " + this.aggregationFunction.falseValue());
            NeuronAssert.state(this.aggregationFunction.trueValue() >= input, "Input : " + input + " is higher than " + this.aggregationFunction.trueValue());
        }
    }

    protected static class ErrorOutput {
        private final double errorPerInput;
        private final double correction;

        protected ErrorOutput(final double errorPerInput, final double correction) {
            this.errorPerInput = errorPerInput;
            this.correction = correction;
        }
    }

    @Override
    public String toString() {
        return "N[" + firstCoefficient + Arrays.toString(coefficients) + "]";
    }
}
