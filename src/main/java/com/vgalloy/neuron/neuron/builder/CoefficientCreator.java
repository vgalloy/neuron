package com.vgalloy.neuron.neuron.builder;

import java.util.Arrays;

import com.vgalloy.neuron.constant.Constant;

/**
 * Created by Vincent Galloy on 19/10/18.
 *
 * @author Vincent Galloy
 */
interface CoefficientCreator {

    double getFirstCoefficient();

    double[] getCoefficients();

    static CoefficientCreator fixed(final double firstCoefficient, final double... coefficients) {
        final double[] coeff = Arrays.copyOf(coefficients, coefficients.length);
        return new CoefficientCreator() {

            @Override
            public double getFirstCoefficient() {
                return firstCoefficient;
            }

            @Override
            public double[] getCoefficients() {
                return coeff;
            }
        };
    }

    static CoefficientCreator fromLength(int length) {
        return new CoefficientCreator() {

            @Override
            public double getFirstCoefficient() {
                return Constant.doubleRandom();
            }

            @Override
            public double[] getCoefficients() {
                return Constant.doubleRandom(length);
            }
        };
    }
}
