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
class SimpleNeuron implements Neuron {

    private static final Long MULTIPLICATOR = Constant.GLOBAL_MULTIPLICATOR * 2 / 10;

    private final List<Long> coefficients;

    SimpleNeuron(List<Long> coefficients) {
        Objects.requireNonNull(coefficients);
        if (coefficients.isEmpty()) {
            throw new IllegalArgumentException("Neuron must have at least on entry point");
        }
        List<Long> fullCoefficients = new ArrayList<>();
        fullCoefficients.add(Constant.MINUS_ONE);
        fullCoefficients.addAll(coefficients);
        this.coefficients = fullCoefficients;
    }

    @Override
    public Boolean apply(List<Boolean> input) {
        if (input.size() + 1 != coefficients.size()) {
            throw new IllegalArgumentException("args size is not correct");
        }

        Long result = 0L;
        for (int i = 0; i < coefficients.size(); i++) {
            result += compute(i, input);
        }

        return result > 0;
    }

    @Override
    public List<Long> train(List<Boolean> input, Long expected) {
        if (input.size() + 1 != coefficients.size()) {
            throw new IllegalArgumentException("args size is not correct");
        }

        Boolean result = apply(input);
        List<Long> coefficientCorrection = new ArrayList<>();
        Long resultAsBigInteger = result ? Constant.ONE : 0L;
        if (!resultAsBigInteger.equals(expected)) {
            for (int i = 0; i < coefficients.size(); i++) {
                Long diff = (expected - compute(i, input)) * MULTIPLICATOR / Constant.GLOBAL_MULTIPLICATOR;
                coefficientCorrection.add(diff);
                coefficients.set(i, coefficients.get(i) + diff);
            }
        }
        return coefficientCorrection.subList(1, coefficientCorrection.size());
    }

    private Long compute(int i, List<Boolean> input) {
        Long valueAsLong = Constant.ONE;
        if (i != 0) {
            valueAsLong = input.get(i - 1) ? Constant.ONE : 0L;
        }
        return coefficients.get(i) * valueAsLong;
    }

    @Override
    public String toString() {
        return "SimpleNeuron{" +
            "coefficients=" + coefficients +
            '}';
    }
}
