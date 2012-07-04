package ar.edu.austral.analisis4.methods.interpolators;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.vecmath.Point2d;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ar.edu.austral.analisis4.functions.OneVariableFunction;

@RunWith(value = Parameterized.class)
public class CubicSplineTest {

	private static final double ERROR = 0.0001;

	private List<Point2d> points;
	private double[] xValues;
	private double[] yValues;
	
	public CubicSplineTest(List<Point2d> points, double[] xValues, double[] yValues) {
		this.points = points;
		this.xValues = xValues;
		this.yValues = yValues;
	}
	
	@Test
	public void testCubicSpline() {
    	OneVariableFunction function = CubicSpline.interpolate(points);
    	
    	for (int i = 0; i < xValues.length; i++) {
   			testAssertEquals(yValues[i], function.eval(xValues[i]));	
    	}
    	printResult(function);
	}
	
	private void printResult(OneVariableFunction function) {
		for (int i = 0; i < xValues.length; i++) {
			double x = xValues[i];
			System.out.println("f("+x+") = "+ function.eval(x));
		}
	}
	
	private void testAssertEquals(double expected, double actual) {
    	assertEquals("The index founded was not the expected", expected, actual, ERROR);
	}
	
	@Parameters
    public static Collection<Object[]> data() {
        Object[][] parameters = new Object[][] {{createPoints(), createX(), createY()}};    
    	return Arrays.asList(parameters);
    }
	
	private static List<Point2d> createPoints() {
		List<Point2d> points = new ArrayList<Point2d>();
    	points.add(new Point2d(40, 390));
    	points.add(new Point2d(45, 340));
    	points.add(new Point2d(50, 290));
    	points.add(new Point2d(55, 250));
    	points.add(new Point2d(60, 210));
    	points.add(new Point2d(65, 180));
    	points.add(new Point2d(70, 160));
        return points;
    }
	
	private static double[] createX() {
		double[] xValues = new double[10]; 
		xValues[0] = 40;
		xValues[1] = 43;
		xValues[2] = 45;
		xValues[3] = 50;
		xValues[4] = 52;
		xValues[5] = 59;
		xValues[6] = 60;
		xValues[7] = 67;
		xValues[8] = 69;
		xValues[9] = 70;
		return xValues;
	}
	
	private static double[] createY() {
		double[] yValues = new double[10];
		yValues[0] = 390.0;
		yValues[1] = 360.29046;
		yValues[2] = 340.0;
		yValues[3] = 290.0;
		yValues[4] = 273.29046;
		yValues[5] = 217.57907;
		yValues[6] = 210.0;
		yValues[7] = 171.26646;
		yValues[8] = 163.63323;
		yValues[9] = 160.0;
		return yValues;
	}
}
