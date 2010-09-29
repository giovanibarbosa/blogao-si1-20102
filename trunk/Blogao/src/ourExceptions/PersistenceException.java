package ourExceptions;

/**
 * Classe que lanca excecoes {@link Exception} na persistencia de dados
 * 
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * 
 */
@SuppressWarnings("serial")
public class PersistenceException extends Exception {

	/**
	 * Construtor da classe
	 * @param mensagem {@link String} a ser exibida no lancamento da excecao 
	 *            
	 */
	public PersistenceException(String mensagem) {
		super(mensagem);
	}

	/**
	 * Construtor auxiliar da classe
	 * 
	 * @param mensagem {@link String} a ser exibida
	 *            
	 * @param e {@link Exception}
	 */
	public PersistenceException(String mensagem, Exception e) {
		super(mensagem, e);
	}
}
