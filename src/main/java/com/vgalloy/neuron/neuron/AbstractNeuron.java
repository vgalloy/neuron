package com.vgalloy.neuron.neuron;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import com.vgalloy.neuron.util.NeuronAssert;

/**
 * Created by Vincent Galloy on 25/09/2018.
 *
 * @author Vincent Galloy
 */
abstract class AbstractNeuron<T extends Number> implements Neuron {

    private final List<T> coefficients;
    private final Function<T, Boolean> activationFunction;

    AbstractNeuron(final T firstCoefficient, final List<T> coefficients, final Function<T, Boolean> activationFunction) {
        NeuronAssert.checkState(!coefficients.isEmpty(), "Neuron must have at least on entry point");
        this.coefficients = new ArrayList<>();
        this.coefficients.add(firstCoefficient);
        this.coefficients.addAll(coefficients);
        this.activationFunction = Objects.requireNonNull(activationFunction, "activationFunction");
    }

    protected abstract T compute(final List<Boolean> input);

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

        final T result = compute(input);
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

    protected List<T> getCoefficients() {
        return coefficients;
    }

    protected Function<T, Boolean> getActivationFunction() {
        return activationFunction;
    }

    protected void checkInputSize(final List<Boolean> input) {
        NeuronAssert.checkState(input.size() == inputSize(), "You are training neuron with " + input.size() + " inputs. But this neuron needs " + inputSize() + ".");
    }
}
