package ar.edu.austral.analisis4.methods.interpolators;

import java.util.List;

import javax.vecmath.Point2d;

import ar.edu.austral.analisis4.functions.OneVariableFunction;

/**
 * <p>Newton's interpolator is a polynomical interpolation that use the Newton Polynomial. polynomial interpolation 
 * is the interpolation of a given data set by a polynomial: given some points, find a polynomial which goes exactly through these points.<p>
 * 
 */
public class NewtonInterpolation {

	/**
	 * Interpolates the function
	 * @param points Set of point to be use
	 * @return OneVariableFunction
	 */
	public static final OneVariableFunction interpolate(final List<Point2d> points) {
		final double[] a = divDif(points);
		return new OneVariableFunction() {
			public double eval(double x) {
				return horner(a, points).eval(x);
			}
		};
	}

	private static double[] divDif(List<Point2d> points) {
		int N = points.size();
		double[][] M = new double[N][N];
		for (int i = 0; i < N; i++)
			M[i][0] = (points.get(i).y);
		for (int j = 1; j < N; j++)
			for (int i = 0; i < N - j; i++)
				M[i][j] = (M[i + 1][j - 1] - M[i][j - 1]) / (points.get(i + j).x - (points.get(i).x));
		return M[0];
	}

	private static final OneVariableFunction horner(final double[] a, final List<Point2d> points) {
		return new OneVariableFunction() {
			public double eval(double x) {
				int n = a.length;
				double v = a[n - 1];
				for (int i = 1; i < n; i++)
					v = v * (x - points.get(n - 1 - i).x) + a[n - 1 - i];
				return v;
			}
		};
	}
}
