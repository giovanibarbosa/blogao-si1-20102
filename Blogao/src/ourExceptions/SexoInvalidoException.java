package ourExceptions;

public class SexoInvalidoException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe
	 * @param mensagem {@link String} a ser exibida no lancamento da excecao 
	 *            
	 */
	public SexoInvalidoException(String mensagem) {
		super(mensagem);
	}

	/**
	 * Construtor auxiliar da classe
	 * 
	 * @param mensagem {@link String} a ser exibida
	 *            
	 * @param e {@link Exception}
	 */
	public SexoInvalidoException(String mensagem, Exception e) {
		super(mensagem, e);
	}
}
