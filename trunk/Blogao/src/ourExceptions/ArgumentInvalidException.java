package ourExceptions;


/**
 * Classe que lanca uma Excessao caso o argumento seja invalido.
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 *
 */
@SuppressWarnings("serial")
public class ArgumentInvalidException extends Exception {
	
	/**
	 * Construtor da classe.
	 * @param problem {@link String}
	 */
	public ArgumentInvalidException(String problem) {
		super(problem);
	}
	
	/**
	 * Construtor auxiliar da classe.
	 * @param problem {@link String}
	 * @param e {@link Exception}
	 */
	public ArgumentInvalidException(String problem, Exception e) {
		super(problem, e);
	}

}
