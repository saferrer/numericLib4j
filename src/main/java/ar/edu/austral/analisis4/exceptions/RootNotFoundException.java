package ar.edu.austral.analisis4.exceptions;

public class RootNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RootNotFoundException(String methodName) {
        super(methodName + ": Root not found");
    }
}
