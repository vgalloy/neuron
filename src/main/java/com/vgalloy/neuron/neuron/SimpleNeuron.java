package com.vgalloy.neuron.neuron;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import com.vgalloy.neuron.constant.Constant;
import com.vgalloy.neuron.util.NeuronAssert;

/**
 * Created by Vincent Galloy on 01/04/17.
 *
 * @author Vincent Galloy
 */
class SimpleNeuron implements Neuron {

    private static final Long MULTIPLICATOR = Constant.GLOBAL_MULTIPLICATOR * 2 / 10;

    private final List<Long> coefficients;

    SimpleNeuron(Long firstCoefficient, List<Long> coefficients) {
        Objects.requireNonNull(firstCoefficient);
        Objects.requireNonNull(coefficients);
        NeuronAssert.checkState(coefficients.isEmpty(), "Neuron must have at least on entry point");
        this.coefficients = new ArrayList<>();
        this.coefficients.add(firstCoefficient);
        this.coefficients.addAll(coefficients);
    }

    @Override
    public Boolean apply(List<Boolean> input) {
        NeuronAssert.checkState(input.size() != coefficients.size() - 1, "You are train neuron with " + input.size() + " inputs. But this neuron needs " + (coefficients.size() - 1) + ".");

        Long result = 0L;
        for (int i = 0; i < coefficients.size(); i++) {
            result += compute(i, input);
        }

        return result > 0;
    }

    @Override
    public List<Long> train(List<Boolean> input, Long expected) {
        Objects.requireNonNull(input, "Input can not be null");
        Objects.requireNonNull(expected, "Expected result can not be null");
        NeuronAssert.checkState(input.size() != coefficients.size() - 1, "You are train neuron with " + input.size() + " inputs. But this neuron needs " + (coefficients.size() - 1) + ".");

        Boolean result = apply(input);
        Long resultAsLong = Constant.map(result);
        List<Long> coefficientCorrection = new ArrayList<>();
        if (!resultAsLong.equals(expected)) {
            for (int i = 0; i < coefficients.size(); i++) {
                Long diff = (expected - compute(i, input)) * MULTIPLICATOR / Constant.GLOBAL_MULTIPLICATOR;
                coefficients.set(i, Math.max(Constant.MINUS_ONE, Math.min(Constant.ONE, coefficients.get(i) + diff)));
                coefficientCorrection.add(diff);
            }
            return coefficientCorrection.subList(1, coefficientCorrection.size());
        }
        return LongStream.range(0, coefficients.size() - 1).boxed().map(e -> 0L).collect(Collectors.toList());
    }

    private Long compute(int i, List<Boolean> input) {
        return getValue(i, input) * coefficients.get(i) / Constant.ONE;
    }

    private Long getValue(int i, List<Boolean> input) {
        if (i == 0) {
            return Constant.ONE;
        }
        return Constant.map(input.get(i - 1));
    }

    @Override
    public String toString() {
        return "SimpleNeuron{" +
            "coefficients=" + coefficients +
            '}';
    }
}
