package ar.edu.austral.analisis4.definiteIntegral.resolvers;

import ar.edu.austral.analisis4.functions.OneVariableFunction;

/**
 * <p>In numerical analysis, Romberg's method (Romberg 1955) is used to estimate the definite integral </p>
 * 
 * <p> The estimates generate a triangular array. Romberg's method is a Newton–Cotes formula – it evaluates the integrand 
 * at equally-spaced points. The integrand must have continuous derivatives, though fairly 
 * good results may be obtained if only a few derivatives exist.</p>
 *
 */
public class Romberg {

	 /**
     * tabulates the function.
     * Evaluates the function in a close interval [a,b]
     * @param f funcion
     * @param a starpoint
     * @param b endpoint
     * @param n amount of intervals
     * @return double[] Tabulated function
     */
    private static double[] tabulate(OneVariableFunction f, double a, double b, int n) {
        double[] t = new double[n+1];
        double h = (b-a)/n;
        for (int i = 0; i < n+1; i++) {
            t[i] = f.eval(a+(i*h));
        }
        return t;
    }


    /**
     * Integrates a tabulated function in 2^k intervals with trapezoid
     * @param f funcion
     * @param a starpoint
     * @param b endpoint
     * @param k amount of intervals
     * @return evaluated value with trapezoid
     */
    public static double trapezoid(double[] t, double a, double b, int k) {
        int n = (int) Math.pow(2,k);
        double h = (b-a)/n;        
        int increment = t.length/n;
        double accum = 0;
        for (int i = increment; i < t.length-1; i+=increment) {
            accum += t[i];
        }
        return (t[0]+2*accum+t[t.length-1])*h/2;
    }

    /**
     * Romberg Integration
     * t size must be 2^k+1.
     * @param t tabulated Function
     * @param a starpoint
     * @param b endpoint
     * @return evaluated value
     */
    private static double romberg(double[] t, double a, double b) {
        int v = (int)(Math.log(t.length) / Math.log(2));
        if( Math.abs(Math.pow(2,v)- (t.length-1)) > 0.0001) {
            throw new IllegalArgumentException("" + t.length);
        }
        double[] rn = new double[v];

        //Trapezoid
        for (int i = 0; i < rn.length; i++) {
            rn[i] = trapezoid(t, a, b, i);
        }

        //Romberg Correction
        for(int k = 1; k < rn.length-1; k++) {
            final double c = Math.pow(4, k);
            int jend = rn.length - k - 1;
            for(int j = 0; j < jend; j++ ) {
                rn[j] = ((c* rn[j+1])- rn[j])/(c-1);
            }
            rn[jend] = Double.NaN;
        }
        return rn[0];
    }
    
    /**
     * Romberg Integration of a tabulated function in 2^k intervals
     * @param f Function
     * @param a starpoint
     * @param b endpoint
     * @param k amount of intervals
     * @return evaluated value
     */
    public static double romberg(OneVariableFunction f, double a, double b, int k) {
        int n = 1 << k;
        double[] t = tabulate(f, a, b, n);
        return romberg(t,a, b);
    }

}
