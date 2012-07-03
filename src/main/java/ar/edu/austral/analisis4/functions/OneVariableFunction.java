package ar.edu.austral.analisis4.functions;

/**
 * This class represents a function with one variable
 * 
 * <b>Example</b>
 * <pre>
 * Function sen(x) + x is created this way:
 * 
 * final OneVariableFunction f1 = new OneVariableFunction(){
 *           public double eval(double x){
 *          	return Math.sen(x) + x;
 *           }
 *      };
 * <pre>
 */
public interface OneVariableFunction {

	/**
	 * Evaluates the function with the given X value.
	 * 
	 * @param x Value to be eval
	 * @return the function value in x
	 */
    public double eval (double x);

}
