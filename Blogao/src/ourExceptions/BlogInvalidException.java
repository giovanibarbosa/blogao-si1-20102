package ourExceptions;

@SuppressWarnings("serial")
public class BlogInvalidException extends Exception {
	/**
	 * Construtor da classe.
	 * @param problem {@link String}
	 */
	public BlogInvalidException(String problem) {
		super(problem);
	}
	
	/**
	 * Construtor auxiliar da classe.
	 * @param problem {@link String}
	 * @param e {@link Exception}
	 */
	public BlogInvalidException(String problem, Exception e) {
		super(problem, e);
	}

}
