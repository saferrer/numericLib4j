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
public class NewtonInterpolationTest {

	private static final double ERROR = 0.00001;

	private List<Point2d> points;
	private OneVariableFunction expectedFunction;
	
	public NewtonInterpolationTest(List<Point2d> points, OneVariableFunction expectedFunction) {
		this.points = points;
		this.expectedFunction = expectedFunction;
	}

	@Test
    public void testLinearInterpolator(){
		OneVariableFunction interpolation = NewtonInterpolation.interpolate(points);
        for(int i = 0; i<7; i++) {
        	testAssertEquals(expectedFunction.eval(i), interpolation.eval(i));
        }
        printResult(interpolation);
    }
	
	private void testAssertEquals(double expected, double actual) {
    	assertEquals("The index founded was not the expected", expected, actual, ERROR);
	}
	
	private static void printResult(OneVariableFunction function) {
    	for(int i = 0; i<7; i++) {
    		System.out.println("f("+i+") = " + function.eval(i));
    	}
    }
    
    private static List<Point2d> createPoints() {
        List<Point2d> points = new ArrayList<Point2d>();
        points.add(new Point2d(0, 0));
        points.add(new Point2d(2, 4));
        points.add(new Point2d(4, 0));
        points.add(new Point2d(5, 8));
        return points;
    }
    
    private static OneVariableFunction createExpectedFunction() {
    	return new OneVariableFunction() {
    		@Override
    		public double eval(double x) {
    			if(x == 0) return 0;
    			if(x == 1) return 5.6;
    			if(x == 2) return 4;
    			if(x == 3) return 0.39999;
    			if(x == 4) return 0;
    			if(x == 5) return 8;
    			if(x == 6) return 29.6;
    			return 0;
    		}
    	};
 	}
     
    @Parameters
    public static Collection<Object[]> data() {
    	Object[][] parameters = new Object[][] {{createPoints(), createExpectedFunction()}};    
    	return Arrays.asList(parameters);
    }
}
