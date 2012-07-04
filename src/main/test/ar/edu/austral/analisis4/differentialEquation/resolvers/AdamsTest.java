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
public class AdamsTest {
	
	private static final double ERROR = 0.000001;

	private TwoVariableFunction function;
	private String functionString;
	double x0, y0, xn, expected;

	
	public AdamsTest(TwoVariableFunction funcion, String functionString, double x0, double y0, double xn, double expected) {
		this.function = funcion;
		this.functionString = functionString;
		this.x0 = x0;
		this.y0 = y0;
		this.xn = xn;
		this.expected = expected;
	}
	
	@Test
	public void calculateTestAdamsBashForthOrder5(){
		Adams order5 = Adams.createOrder5();
		double actual = order5.adamsBashforth(function, x0, y0, xn);
		assertEquals("the expected value does not match the result", expected, actual, ERROR);
		System.out.println("Function: y'=" + functionString + ", x0=" + x0 + ", y0=" + y0 + ", xn=" + xn);
		System.out.println("Expected value: "+expected);
		System.out.println("Result: "+actual);
	}
	
	@Test
	public void calculateTestAdamsMoultonOrder5() {
		Adams order5 = Adams.createOrder5();
		double actual = order5.adamsMoulton(function, x0, y0, xn, 10, 0.000000001);
		assertEquals("the expected value does not match the result", expected, actual, ERROR);
		System.out.println("Function: y'=" + functionString + ", x0=" + x0 + ", y0=" + y0 + ", xn=" + xn);
		System.out.println("Expected value: "+expected);
		System.out.println("Result: "+actual);
	}
	
	@Test
	public void calculateTestAdamsBashForthOrder6(){
		Adams order5 = Adams.createOrder6();
		double actual = order5.adamsBashforth(function, x0, y0, xn);
		assertEquals("the expected value does not match the result", expected, actual, ERROR);
		System.out.println("Function: y'=" + functionString + ", x0=" + x0 + ", y0=" + y0 + ", xn=" + xn);
		System.out.println("Expected value: "+expected);
		System.out.println("Result: "+actual);
	}
	
	@Test
	public void calculateTestAdamsMoultonOrder6() {
		Adams order5 = Adams.createOrder6();
		double actual = order5.adamsMoulton(function, x0, y0, xn, 10, 0.000000001);
		assertEquals("the expected value does not match the result", expected, actual, ERROR);
		System.out.println("Function: y'=" + functionString + ", x0=" + x0 + ", y0=" + y0 + ", xn=" + xn);
		System.out.println("Expected value: "+expected);
		System.out.println("Result: "+actual);
	}
	
	@Test
	public void calculateTestAdamsBashForthOrder7(){
		Adams order5 = Adams.createOrder7();
		double actual = order5.adamsBashforth(function, x0, y0, xn);
    	assertEquals("The value expected is not", expected, actual, ERROR);
		System.out.println("Function: y'=" + functionString + ", x0=" + x0 + ", y0=" + y0 + ", xn=" + xn);
		System.out.println("Expected value: "+expected);
		System.out.println("Result: "+actual);
	}
	
	@Test
	public void calculateTestAdamsMoultonOrder7() {
		Adams order5 = Adams.createOrder7();
		double actual = order5.adamsMoulton(function, x0, y0, xn, 10, 0.000000001);
		assertEquals("the expected value does not match the result", expected, actual, ERROR);
		System.out.println("Function: y'=" + functionString + ", x0=" + x0 + ", y0=" + y0 + ", xn=" + xn);
		System.out.println("Expected value: "+expected);
		System.out.println("Result: "+actual);
	}
	private static TwoVariableFunction createFunction1() {
		return new TwoVariableFunction() {
			@Override
			public double eval(double x, double y) {
				return 4*Math.exp(0.8*x) - 0.5*y;   //4 * e^(0.8x) - 0.5y
			}
    	};
	}
	
	@Parameters
    public static Collection<Object[]> data() {
        final String functionString = "4 * e^(0.8x) - 0.5y";
        
        Object[][] parameters = new Object[][] {{createFunction1() , functionString, 0, 2, 0.5, 3.751521}}; 
    	return Arrays.asList(parameters);
    }
}
