package classes;

import interfaces.Constantes;


import ourExceptions.ArgumentInvalidException;

/**
 * Classe utilizada para gerar um novo email.
 * 
 * @author Ana Clara Lacerda - anacls@lcc.ufcg.edu.br
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 *
 * 
 */
public class Email {

	private String email;

	/**
	 * Construtor da objeto.
	 * 
	 * @param email
	 *              {@link String}
	 * @throws Exception
	 *             caso o email seja vazio ou null
	 */
	public Email(String email) throws ArgumentInvalidException {
		setEmail(email);
	}

	/**
	 * Metodo que seta um email de um determinado {@link Usuario}.
	 * 
	 * @param novoEmail 
	 * 			{@link String}
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
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Email))
			return false;
		Email email = (Email) obj;
		return toString().equals(email.toString());
	}
	
}
