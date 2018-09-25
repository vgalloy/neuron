package com.vgalloy.neuron.neuron;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import com.vgalloy.neuron.constant.Constant;
import com.vgalloy.neuron.util.NeuronAssert;

/**
 * Created by Vincent Galloy on 25/09/2018.
 *
 * @author Vincent Galloy
 */
abstract class AbstractNeuron implements Neuron {

    private final List<Double> coefficients;
    private final Function<Double, Boolean> activationFunction;

    AbstractNeuron(final Double firstCoefficient, final List<Double> coefficients, final Function<Double, Boolean> activationFunction) {
        NeuronAssert.checkState(!coefficients.isEmpty(), "Neuron must have at least on entry point");
        this.coefficients = new ArrayList<>();
        this.coefficients.add(firstCoefficient);
        this.coefficients.addAll(coefficients);
        this.activationFunction = Objects.requireNonNull(activationFunction, "activationFunction");
    }

    protected Double compute(final List<Boolean> input) {
        final NeuronInput neuronInput = NeuronInput.of(input);
        double result = 0;
        for (int i = 0; i < getCoefficients().size(); i++) {
            result +=  compute(neuronInput, i);
        }
        return result;
    }

    protected abstract double compute(final NeuronInput neuronInput, final int i);
//
//    private T compute(final List<Boolean> input) {
//        final NeuronInput neuronInput = NeuronInput.of(input);
//        T result = (T) (Number) 0;
//        for (int i = 0; i < getCoefficients().size(); i++) {
//            T value = compute(neuronInput, i);
//            result = result + value;
//        }
//        return result;
//    }

    @Override
    public boolean apply(final List<Boolean> input) {
        checkInputSize(input);

        final Double result = compute(input);
        return getActivationFunction().apply(result);
    }
//
//    @Override
//    public List<Double> train(final List<Boolean> input, final boolean expected) {
//        checkInputSize(input);
//        return null;
//    }

    @Override
    public int inputSize() {
        return coefficients.size() - 1;
    }

    protected List<Double> getCoefficients() {
        return coefficients;
    }

    protected Function<Double, Boolean> getActivationFunction() {
        return activationFunction;
    }

    protected void checkInputSize(final List<Boolean> input) {
        NeuronAssert.checkState(input.size() == inputSize(), "You are training neuron with " + input.size() + " inputs. But this neuron needs " + inputSize() + ".");
    }
}
