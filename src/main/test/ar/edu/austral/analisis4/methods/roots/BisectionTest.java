package ar.edu.austral.analisis4.methods.roots;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ar.edu.austral.analisis4.exceptions.RootNotFoundException;
import ar.edu.austral.analisis4.functions.OneVariableFunction;

@RunWith(value = Parameterized.class)
public class BisectionTest {
	
	private OneVariableFunction function;
	private String functionString;
	private double expected;
	private double a;
	private double b;
	private double error;
	private int iterations;
	
	public BisectionTest(OneVariableFunction function, String functionString,
			 double a, double b, double error, int iterations, double expected) {
		super();
		this.function = function;
		this.functionString = functionString;
		this.a = a;
		this.b = b;
		this.error = error;
		this.iterations = iterations;
		this.expected = expected;
	}

	@Parameters
    public static Collection<Object[]> data() {
    	//Function 1
    	final OneVariableFunction f1 = new OneVariableFunction(){
            public double eval(double x){
            	return Math.pow(Math.E, -x) - Math.log(x) / Math.log(Math.E);
            }
        };
        final String f1String = "e^(-x) - ln x";
        double expectedRoot1 = 1.30975341796875;
        
        //Function 2
    	final OneVariableFunction f2 = new OneVariableFunction(){
            public double eval(double x){
                return  Math.pow(x,3) + 4 * Math.pow(x,2) - 10;
            }
        };
        final String f2String = "x^3 + 4x^2 - 10";
        double expectedRoot2 = 1.36517333984375;
        
        final String f3String = "Function will Fail";
        
        Object[][] parameters = new Object[][] {{f1 , f1String, 1, 2, 0.0001, 14, expectedRoot1}, 
        										{f2, f2String, 1, 1.5, 0.0001, 13, expectedRoot2},
        										{f2, f3String, 1, 1.5, 0.0001, 5, expectedRoot2}};    
    	return Arrays.asList(parameters);
    }
	
	@Test
	public void calculateTest(){
		try {
			double calculateRoot =  Bisection.findRoot(function, a, b, error, iterations);
			System.out.println("Function: " + functionString + "\n Calculated root: " + calculateRoot + " Expected Root: " + expected);
			assertEquals("The root founded was not the expected", expected, calculateRoot, 0.0001);
		} catch (RootNotFoundException e) {
			if(!functionString.equalsIgnoreCase("Function will Fail")){
				e.printStackTrace();
				fail("Root Not Found Exception thrown");
			}else{
				System.out.println("OK: Root Not Found Exception expected and thrown");
			}
		}
	}
}
