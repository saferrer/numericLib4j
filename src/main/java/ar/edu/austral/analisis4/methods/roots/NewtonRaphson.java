package ar.edu.austral.analisis4.methods.roots;

import ar.edu.austral.analisis4.exceptions.RootNotFoundException;
import ar.edu.austral.analisis4.functions.OneVariableFunction;

/**
 * <p>
 * In numerical analysis, Newton's method (also known as the Newton–Raphson method), 
 * named after Isaac Newton and Joseph Raphson, is a method for finding successively 
 * better approximations to the roots (or zeroes) of a real-valued function.
 * </p>
 * <br>
 * <p>
 * The Newton-Raphson method in one variable is implemented as follows:
 * Given a function ƒ defined over the reals x, and its derivative ƒ ', 
 * we begin with a first guess x0 for a root of the function f. 
 * Provided the function is reasonably well-behaved a better approximation x1 is
 * </p>
 * <img src="..\resources\NewtonRapshonMethod1.png" > <br>
 * <p>
 * Geometrically, (x1, 0) is the intersection with the x-axis of a line tangent to f at (x0, f (x0)). The process is repeated as
 * </p>
 * <img alt="NewtonRaphson Formule" src="..\resources\NewtonRapshonMethod2.png" > <br>
 */
public class NewtonRaphson{
	
	private static final String METHOD_NAME = "Newton Raphson Method";
	
    /**
     * @param f function
     * @param fderived derived function
     * @param p0 point of start
     * @param error tolerated error level
     * @param maxIterations max number of iterations
     * @return the root of the function
     * @throws RootNotFoundException in case there is no root or the function does not apply
	 * the minimum requirements.
     */
    public static double findRoot(OneVariableFunction f, OneVariableFunction fderived, double p0, double error, int maxIterations)
            throws RootNotFoundException {
        int iterations = 1;
        double p = p0;
        while (iterations < maxIterations) {
            //Xn+1 = Xn - f(x) / f'(x)
            double p1 = p - f.eval(p) / fderived.eval(p);
            if (Math.abs(f.eval(p1)) < error) {
                return p1;
            }
            p = p1;
            iterations++;
        }
        throw new RootNotFoundException(METHOD_NAME);
    }
}
