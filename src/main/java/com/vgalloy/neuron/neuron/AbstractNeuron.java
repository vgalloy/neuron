package com.vgalloy.neuron.neuron;

import java.util.ArrayList;
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

    private final List<Double> coefficients;
    private final AggregationFunction aggregationFunction;

    AbstractNeuron(final Double firstCoefficient, final List<Double> coefficients, final AggregationFunction aggregationFunction) {
        NeuronAssert.checkState(!coefficients.isEmpty(), "Neuron must have at least on entry point");
        this.coefficients = new ArrayList<>();
        this.coefficients.add(firstCoefficient);
        this.coefficients.addAll(coefficients);
        this.aggregationFunction = Objects.requireNonNull(aggregationFunction, "aggregationFunction");
    }

    protected Double compute(final List<Boolean> input) {
        final NeuronInput neuronInput = NeuronInput.of(input);
        double result = 0;
        for (int i = 0; i < getCoefficients().size(); i++) {
            result +=  compute(neuronInput, i);
        }
        return result;
    }

    protected double compute(final NeuronInput neuronInput, final int i) {
        return Constant.mapBoolean(neuronInput.get(i)) * getCoefficients().get(i);
    }

    @Override
    public boolean apply(final List<Boolean> input) {
        checkInputSize(input);

        final Double result = compute(input);
        return getAggregationFunction().apply(result) > 0;
    }

    @Override
    public List<Double> train(final List<Boolean> input, final boolean expected) {
        Objects.requireNonNull(input, "NeuronInput can not be null");
        checkInputSize(input);

        final boolean result = apply(input);

        final double diff = Constant.mapBoolean(expected) - Constant.mapBoolean(result);
        return train(input, diff);
    }


    @Override
    public List<Double> train(final List<Boolean> input, final double diff) {
        Objects.requireNonNull(input, "NeuronInput can not be null");

        final double result = compute(input);
        final double error = getAggregationFunction().applyDerived(result) * diff;

        final List<Double> coefficientCorrection = new ArrayList<>();
        final NeuronInput neuronInput = NeuronInput.of(input);
        for (int i = 0; i < neuronInput.size(); i++) {
            final double errorPerInput = error * getCoefficients().get(i);
            coefficientCorrection.add(errorPerInput);
            final double newCoefficient = getCoefficients().get(i) + error * Constant.mapBoolean(neuronInput.get(i)) * LEARNING_MULTIPLICATOR;
            getCoefficients().set(i, newCoefficient);
        }
        return coefficientCorrection.subList(1, coefficientCorrection.size());
    }

    @Override
    public int inputSize() {
        return coefficients.size() - 1;
    }

    protected List<Double> getCoefficients() {
        return coefficients;
    }

    protected AggregationFunction getAggregationFunction() {
        return aggregationFunction;
    }

    protected void checkInputSize(final List<Boolean> input) {
        NeuronAssert.checkState(input.size() == inputSize(), "You are training neuron with " + input.size() + " inputs. But this neuron needs " + inputSize() + ".");
    }
}
