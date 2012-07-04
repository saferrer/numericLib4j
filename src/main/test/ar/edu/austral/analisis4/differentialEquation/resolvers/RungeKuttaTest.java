package ar.edu.austral.analisis4.differentialEquation.resolvers;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ar.edu.austral.analisis4.functions.TwoVariableFunction;

@RunWith(value = Parameterized.class)
public class RungeKuttaTest {
	
	private static final double ERROR = 0.0001;

	private TwoVariableFunction funcion;
	private double x;
	private double y;
	private double h;
	private double expected;
	
	public RungeKuttaTest(TwoVariableFunction funcion, double x, double y, double h, double expected) {
		this.funcion = funcion;
		this.x = x;
		this.y = y;
		this.h = h;
		this.expected = expected;
	}
	
	@Test
	public void calculateTest(){
		RungeKutta rungeKutta = new RungeKutta();
		double actual = rungeKutta.order4(funcion, x, y, h);
    	assertEquals("The index founded was not the expected", expected, actual, ERROR);
		System.out.println("h=0.5 : " + actual);
	}
	
	 @Parameters
     public static Collection<Object[]> data() {
         Object[][] parameters = new Object[][] {{createFunction(), 0, 2, 0.5, 3.75169}};    
     	return Arrays.asList(parameters);
     }
     
     /*
      * Create the function: f(x, y)= 4 * e^(0.8x) - 0.5y
      */
     private static TwoVariableFunction createFunction() {
 		return new TwoVariableFunction() {
 			@Override
 			public double eval(double x, double y) {
 				return 4 * Math.exp(0.8 * x) - 0.5 * y;
 			}
 		};
 	}
}
