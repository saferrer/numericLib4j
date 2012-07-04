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
public class NewtonRaphsonTest {
	
	private OneVariableFunction function;
	private OneVariableFunction derivedFunction;
	private String functionString;
	private double initp;
	private double error;
	private int maxIter;
	private double expectedRoot;
	
	public NewtonRaphsonTest(OneVariableFunction function, OneVariableFunction derivedFunction, String functionString,
			double initp, double error, int maxIter, double expectedRoot) {
		super();
		this.function = function;
		this.derivedFunction = derivedFunction;
		this.functionString = functionString;
		this.initp = initp;
		this.error = error;
		this.maxIter = maxIter;
		this.expectedRoot = expectedRoot;
	}

	@Parameters
    public static Collection<Object[]> data() {
    	//Function 1
    	final OneVariableFunction f1 = new OneVariableFunction(){
            public double eval(double x){
            	return Math.cos(x) - Math.pow(x, 3);
            }
        };
        //Derived Function 1
    	final OneVariableFunction derivedf1 = new OneVariableFunction(){
            public double eval(double x){
            	return -1 * Math.sin(x) - 3 * Math.pow(x, 2);
            }
        };
        final String f1String = "cos(x) - x^3";
        double expectedRoot1 = 0.865474033111;
        
        //Function 2
    	final OneVariableFunction f2 = new OneVariableFunction(){
            public double eval(double x){
                return  4 * Math.pow(x,2) - 10;
            }
        };
        //Derived Function 2
    	final OneVariableFunction derivedf2 = new OneVariableFunction(){
            public double eval(double x){
            	return 8 * x;
            }
        };
        final String f2String = "4x^2 - 10";
        double expectedRoot2 = 1.58113883;
        
        final String failString = "Function will Fail";
        
        Object[][] parameters = new Object[][] {{f1, derivedf1, f1String, 0.5, 0.00001, 6, expectedRoot1},
        										{f2, derivedf2, f2String, 1.5, 0.00001, 6, expectedRoot2},
        										{f1, derivedf1, failString, 0.5, 0.00001, 3, expectedRoot1}};    
    	return Arrays.asList(parameters);
    }
	
	@Test
	public void calculateTest(){
		try {
			double calculateRoot =  NewtonRaphson.findRoot(function, derivedFunction, initp, error, maxIter);
			System.out.println("Function: " + functionString + "\n Calculated root: " + calculateRoot + " Expected Root: " + expectedRoot);
			assertEquals("The root founded was not the expected", expectedRoot, calculateRoot, 0.00001);
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
