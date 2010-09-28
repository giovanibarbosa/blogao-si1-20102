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
	 * @param {@link String} problema
	 */
	public UserInvalidException(String problem) {
		super(problem);
	}
	
	/**
	 * Construtor auxiliar da classe.
	 * @param {@link String} problema
	 * @param {@link Exception} e
	 */
	public UserInvalidException(String problem, Exception e) {
		super(problem, e);
	}

}
