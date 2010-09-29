package ourExceptions;


/**
 * Classe que lanca uma Excecao caso o argumento seja invalido.
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 *
 */
@SuppressWarnings("serial")
public class UserInvalidException extends Exception {
	
	/**
	 * Construtor da classe.
	 * @param problem {@link String}
	 */
	public UserInvalidException(String problem) {
		super(problem);
	}
	
	/**
	 * Construtor auxiliar da classe.
	 * @param problem {@link String}
	 * @param e {@link Exception}
	 */
	public UserInvalidException(String problem, Exception e) {
		super(problem, e);
	}

}
