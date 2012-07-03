package ar.edu.austral.analisis4.functions;

/**
 * This class represents a function with one variable
 * 
 * <b>Example</b>
 * <pre>
 * Function sen(x) + y is created this way:
 * 
 * final TwoVariableFunction f2 = new TwoVariableFunction(){
 *           public double eval(double x, double y){
 *          	return Math.sen(x) - y;
 *           }
 *      };
 * <pre>
 */
public interface TwoVariableFunction {

	/**
	 * Evaluates the function with the given X and Y values.
	 * 
	 * @param x Value to be eval
	 * @param y Value to be eval
	 * @return the function value in x and y
	 */
	public double eval (double x, double y);

}