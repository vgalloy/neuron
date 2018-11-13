package com.vgalloy.neuron.neuron;

/**
 * Created by Vincent Galloy on 25/09/2018.
 *
 * @author Vincent Galloy
 */
public interface AggregationFunction extends BooleanConverter {

    double apply(double x);

    boolean activation(double x);

    double applyDerived(double x);

    AggregationFunction IDENTITY = new AggregationFunction() {
        @Override
        public double apply(double x) {
            return x;
        }

        @Override
        public boolean activation(final double x) {
            return apply(x) > 0;
        }

        @Override
        public double applyDerived(double x) {
            return 1;
        }

        @Override
        public double trueValue() {
            return 1;
        }

        @Override
        public double falseValue() {
            return -1;
        }
    };

    AggregationFunction TAN_H = new AggregationFunction() {
        @Override
        public double apply(double x) {
            return Math.tanh(x);
        }

        @Override
        public boolean activation(final double x) {
            return apply(x) > 0;
        }

        @Override
        public double applyDerived(double x) {
            return 1 - Math.pow(Math.tanh(x), 2);
        }

        @Override
        public double trueValue() {
            return 1;
        }

        @Override
        public double falseValue() {
            return -1;
        }
    };

    AggregationFunction SIGMOID = new AggregationFunction() {
        @Override
        public double apply(final double x) {
            return 1d / (1 + Math.exp(-x));
        }

        @Override
        public boolean activation(final double x) {
            return apply(x) > 0.5;
        }

        @Override
        public double applyDerived(final double x) {
            return apply(x) * (1 - apply(x));
        }

        @Override
        public double trueValue() {
            return 1;
        }

        @Override
        public double falseValue() {
            return 0;
        }
    };
}
