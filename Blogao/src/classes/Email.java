package classes;

import interfaces.Constantes;


import ourExceptions.ArgumentInvalidException;

/**
 * Classe utilizada para gerar um novo email.
 * 
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 * 
 */
public class Email {

	private String email;

	/**
	 * Construtor da objeto.
	 * 
	 * @param String
	 *            email
	 * @throws Exception
	 *             caso o email seja vazio ou null
	 */
	public Email(String email) throws ArgumentInvalidException {
		setEmail(email);
	}

	/**
	 * Metodo que seta um email de um determinado {@link Usuario}.
	 * 
	 * @param String
	 *            email desejado
	 * @throws Exception
	 *             caso o email seja vazio ou null.
	 */
	public void setEmail(String novoEmail) throws ArgumentInvalidException {
		if (novoEmail == null || novoEmail.trim().isEmpty()) {
			throw new ArgumentInvalidException(Constantes.EMAIL_INVALIDO);
		}
		this.email = novoEmail;
	}

	/**
	 * Recupera o formato em {@link String} do {@link Email}
	 * 
	 * @return O {@link Email} no formato {@link String}
	 */
	public String toString() {
		return email;
	}
	
	/**
	 * Metodo que verifica a igualdade entre objetos Blog.
	 * @return True caso os objetos sejam iguais 
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Email))
			return false;
		Email email = (Email) obj;
		return toString().equals(email.toString());
	}
	
}
