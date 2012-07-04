package ar.edu.austral.analisis4.differentialEquation.resolvers;

import ar.edu.austral.analisis4.functions.TwoVariableFunction;

/**
 * <p>In numerical analysis, the Runge–Kutta methods are an important family of implicit and explicit 
 * iterative methods for the approximation of solutions of ordinary differential equations. 
 * These techniques were developed around 1900 by the German mathematicians C. Runge and M.W. Kutta. </p>
 * 
 * <p>One member of the family of Runge–Kutta methods is so commonly used that it is often referred to as "RK4", 
 * "classical Runge–Kutta method" or simply as "the Runge–Kutta method". </p>
 * 
 * <p>Let an initial value problem be specified as follows.<p>
 * 
 * <img src="../../resources/RungeKutta1.png"/>
 * <br>
 * <p>In words, what this means is that the rate at which y changes is a function of y itself and of t (time). 
 * At the start, time is  and y is . In the equation, y may be a scalar or a vector. 
 * The RK4 method for this problem is given by the following equations: </p>
 * 
 * <img src="../../resources/RungeKutta2.png"/>
 * <br>
 */

public class RungeKutta {


	public double order4(TwoVariableFunction f, double x0, double y0, double h) {
		double F1 = f.eval(x0, y0);
		double F2 = f.eval(x0 + 0.5 * h, y0 + 0.5 * h * F1);
		double F3 = f.eval(x0 + 0.5 * h, y0 + 0.5 * h * F2);
		double F4 = f.eval(x0 + h, y0 + h * F3);
		return y0 + (h * (F1 + 2 * (F2 + F3) + F4)) / 6;
	}

    public double[] tabulateOrder4(TwoVariableFunction f, double x0, double y0, double xn, int n) {
        double[] table = new double[n+1];
        double h = (xn-x0)/n;
        table[0] = y0;
        for (int i = 0; i < n; i++) {
            y0 = order4(f,x0,y0, h);
            x0 += h;
            table[i+1] = y0;
        }
        return table;
    }
}
