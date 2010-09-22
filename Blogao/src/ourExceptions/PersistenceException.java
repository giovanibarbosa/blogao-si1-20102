package ourExceptions;

/**
 * Classe que lanca excecoes {@link Exception} na persistencia de dados
 * 
 * @author giovanicb
 * 
 */
@SuppressWarnings("serial")
public class PersistenceException extends Exception {

	/**
	 * Construtor da classe
	 * 
	 * @param mensagem
	 *            A mensagem a ser exibida no lancamento da excecao
	 */
	public PersistenceException(String mensagem) {
		super(mensagem);
	}

	/**
	 * Construtor auxiliar da classe
	 * 
	 * @param mensagem
	 *            A mensagem a ser exibida
	 * @param e
	 */
	public PersistenceException(String mensagem, Exception e) {
		super(mensagem, e);
	}
}
