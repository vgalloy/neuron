package com.vgalloy.neuron.neuron.builder;

import java.util.Objects;

import com.vgalloy.neuron.neuron.impl.ImmutableNeuron;
import com.vgalloy.neuron.neuron.impl.StandardNeuron;
import com.vgalloy.neuron.neuron.AggregationFunction;
import com.vgalloy.neuron.neuron.Neuron;
import com.vgalloy.neuron.util.NeuronAssert;

/**
 * Created by Vincent Galloy on 19/10/18.
 *
 * @author Vincent Galloy
 */
public class Builder implements TypeBuilder, LengthBuilder, NeuronBuilder {

    private AggregationFunction aggregationFunction;
    private CoefficientCreator coefficientCreator;

    /**
     * Constructor.
     * Private to avoid non managed instantiation
     */
    private Builder() {

    }

    public static TypeBuilder builder() {
        return new Builder();
    }

    @Override
    public LengthBuilder withType(final AggregationFunction aggregationFunction) {
        this.aggregationFunction = Objects.requireNonNull(aggregationFunction, "aggregationFunction");
        return this;
    }

    @Override
    public NeuronBuilder inputSize(final int length) {
        NeuronAssert.state(0 < length, "length must be positive");
        this.coefficientCreator = CoefficientCreator.fromLength(length);
        return this;
    }

    @Override
    public NeuronBuilder withCoefficient(final double firstCoefficient, final double... coefficients) {
        this.coefficientCreator = CoefficientCreator.fixed(firstCoefficient, coefficients);
        return this;
    }

    @Override
    public NeuronBuilder withCoefficient(final boolean firstCoefficient, final boolean... coefficients) {
        this.coefficientCreator = CoefficientCreator.fixed(this.aggregationFunction.toDouble(firstCoefficient), this.aggregationFunction.toDoubleArray(coefficients));
        return this;
    }

    @Override
    public AggregationFunction getFunction() {
        return this.aggregationFunction;
    }

    @Override
    public Neuron build() {
        return build(false);
    }

    @Override
    public Neuron build(final boolean isImmutable) {
        if(isImmutable) {
            return new ImmutableNeuron(coefficientCreator.getFirstCoefficient(), aggregationFunction, coefficientCreator.getCoefficients());
        }
        return new StandardNeuron(coefficientCreator.getFirstCoefficient(), aggregationFunction, coefficientCreator.getCoefficients());
    }
}


