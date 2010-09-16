package ourExceptions;


/**
 * Classe que lanca uma Excecao caso o argumento seja invalido.
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 *
 */
@SuppressWarnings("serial")
public class ArgumentInvalidException extends Exception {
	
	/**
	 * Construtor da classe.
	 * @param String problema
	 */
	public ArgumentInvalidException(String problem) {
		super(problem);
	}
	
	/**
	 * Construtor auxiliar da classe.
	 * @param String problem
	 * @param Exception e
	 */
	public ArgumentInvalidException(String problem, Exception e) {
		super(problem, e);
	}

}
