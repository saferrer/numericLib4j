package ar.edu.austral.analisis4.methods.interpolators;

import java.util.List;

import javax.vecmath.Point2d;

import ar.edu.austral.analisis4.functions.OneVariableFunction;

/**
 * <p>Linear interpolation is a method of curve fitting using linear polynomials.
 * If the two known points are given by the coordinates  and , 
 * the linear interpolant is the straight line between these points. 
 * For a value x in the interval , the value y along the straight line is given from the equation</p>
 * <br>
 * 
 * <img src="LinearInterpolation1.png"/>
 *
 * <p>which can be derived geometrically from the figure on the right. 
 * It is a special case of polynomial interpolation with n = 1.
 * Solving this equation for y, which is the unknown value at x, gives</p>
 * <br>
 *
 * <img src="LinearInterpolation2.png"/>
 */
public class LinearInterpolation {

	/**
	 * Interpolates the function
	 * @param points Set of point to be use
	 * @return OneVariableFunction
	 */
	public static final OneVariableFunction interpolate(final List<Point2d> points) {
		return new OneVariableFunction() {
			public double eval(double x) {
				double w = 0;
				if (x <= points.get(0).x)
					w = points.get(0).y + (points.get(1).y - points.get(0).y) * (x - points.get(0).x) / (points.get(1).x - points.get(0).x);
				if (x > points.get(0).x) {
					int i = 0;
					for (int k = 1; k < points.size(); k++) {
						if (x - points.get(i).x > 0)
							i++;
					}
					i--;
					w = points.get(i).y + (points.get(i+1).y - points.get(i).y) * (x - points.get(i).x) / (points.get(i+1).x - points.get(i).x);
				}
				return w;
			}
		};
	}

}
