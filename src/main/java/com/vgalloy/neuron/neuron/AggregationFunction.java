package com.vgalloy.neuron.neuron;

/**
 * Created by Vincent Galloy on 25/09/2018.
 *
 * @author Vincent Galloy
 */
public interface AggregationFunction {

    double apply(double x);

    double applyDerived(double x);

    AggregationFunction IDENTITY = new AggregationFunction() {
        @Override
        public double apply(double x) {
            return x;
        }

        @Override
        public double applyDerived(double x) {
            return 1;
        }
    };

    AggregationFunction TAN_H = new AggregationFunction() {
        @Override
        public double apply(double x) {
            return Math.tanh(x);
        }

        @Override
        public double applyDerived(double x) {
            return 1 - Math.pow(Math.tanh(x), 2);
        }
    };

}
