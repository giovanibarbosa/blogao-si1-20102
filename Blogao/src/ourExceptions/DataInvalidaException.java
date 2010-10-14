package ourExceptions;

public class DataInvalidaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3484447893979304188L;

	/**
	 * Construtor da classe
	 * @param mensagem {@link String} a ser exibida no lancamento da excecao 
	 *            
	 */
	public DataInvalidaException(String mensagem) {
		super(mensagem);
	}

	/**
	 * Construtor auxiliar da classe
	 * 
	 * @param mensagem {@link String} a ser exibida
	 *            
	 * @param e {@link Exception}
	 */
	public DataInvalidaException(String mensagem, Exception e) {
		super(mensagem, e);
	}
}
