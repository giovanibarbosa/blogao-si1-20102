package classes;

import java.io.UnsupportedEncodingException;

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
	public Email(String email) throws Exception {
		setEmail(email);
	}

	/**
	 * Metodo que seta um email de um determinado usuario.
	 * 
	 * @param String
	 *            email desejado
	 * @throws Exception
	 *             caso o email seja vazio ou null.
	 */
	public void setEmail(String novoEmail) throws ArgumentInvalidException {
		if (novoEmail == null || novoEmail.trim().isEmpty()) {
			throw new ArgumentInvalidException("Email inválido");
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
	 * Metodo que codifica a String para o padrao ISO.
	 * @param string
	 * @return
	 */
	private String codificaString(String string) {
		String retorno = "";
		try {
			retorno = new String(string.getBytes(), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return retorno;
	}
}
