package ar.edu.austral.analisis4.methods.roots;

import ar.edu.austral.analisis4.exceptions.RootNotFoundException;
import ar.edu.austral.analisis4.functions.OneVariableFunction;

/**
 * <p>The <b>bisection method</b> in mathematics is a root-finding method which repeatedly bisects an 
 * interval and then selects a subinterval in which a root must lie for further processing. 
 * It is a very simple and robust method, but it is also relatively slow. 
 * Because of this, it is often used to obtain a rough approximation to a solution 
 * which is then used as a starting point for more rapidly converging methods.
 * The method is also called the binary search method.
 * <br>
 * The method is applicable when we wish to solve the equation f(x) = 0 for the real variable x, 
 * where f is a continuous function defined on an interval [a, b] and f(a) and f(b) have opposite signs. 
 * In this case a and b are said to bracket a root since, by the intermediate value theorem, 
 * the f must have at least one root in the interval (a, b).
 * </p>
 * <br>
 * <img alt="Bisection" src="BisectionMethod.png" >
 */
public class Bisection {

	private static final String METHOD_NAME = "Bisection Method";

	/**
	 * @param f Function
	 * @param a left value
	 * @param b right value
	 * @param error tolerated error level
	 * @param iterations max number of iterations
	 * @return the root of the function
	 * @throws RootNotFoundException in case there is no root or the function does not apply
	 * the minimum requirements.
	 */

	public static double findRoot(OneVariableFunction f, double a, double b, double error, int iterations)
			throws RootNotFoundException {

		int i = 1;
		double fa = f.eval(a); // f evaluated in a

		while (i <= iterations) {
			double p = a + (b - a) / 2; //middle point of the interval
			double fp = f.eval(p); //f evaluated in p

			//if fp is root OR the interval is smaller than the tolerated error
			if (fp == 0 || (b - a) / 2 < error) {
				return p;
			}

			i += 1;

			//if fa and fp has the same sign
			if (fa * fp > 0) {
				a = p;
				fa = fp;
			} else {
				b = p;
			}
		}
		throw new RootNotFoundException(METHOD_NAME);
	}
}
