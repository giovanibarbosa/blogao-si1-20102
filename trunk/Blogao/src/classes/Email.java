package classes;

import ourExceptions.ArgumentInvalidException;

/**
 * Classe utilizada para gerar um novo email.
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 *
 */
public class Email {
	
	private String email;
	
	/**
	 * Construtor da objeto.
	 * @param String email
	 * @throws Exception caso o email seja vazio ou null
	 */
	public Email(String email) throws Exception {
		setEmail(email);
	}
	
	/**
	 * Metodo que seta um email de um determinado usuario.
	 * @param String email desejado
	 * @throws Exception caso o email seja vazio ou null.
	 */
	public void setEmail(String novoEmail) throws ArgumentInvalidException {
		if (novoEmail == null || novoEmail == "") {
			throw new ArgumentInvalidException("Email inválido");			
		}			
		this.email = novoEmail;
	}

}
