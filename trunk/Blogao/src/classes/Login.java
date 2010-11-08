package classes;

import enuns.Constantes;
import ourExceptions.ArgumentInvalidException;

/**
 * Classe que inicializa um {@link Login}
 * 
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 *
 */
public class Login {

	private String login;

	/**
	 * Construtor do Login
	 * 
	 * @param login
	 *            {@link String}
	 * @throws {@link ArgumentInvalidException} caso o login seja nulo ou vazio
	 */
	public Login(String login) throws ArgumentInvalidException {
		setLogin(login);
	}

	/**
	 * Metodo modificador que seta um novo login
	 * 
	 * @param login
	 *            {@link String}
	 * @throws {@link ArgumentInvalidException} caso o login seja nulo ou vazio
	 */
	public void setLogin(String login) throws ArgumentInvalidException {
		if (!validaLogin(login)) {
			throw new ArgumentInvalidException(Constantes.LOGIN_INVALIDO.getName());
		}
		this.login = login;

	}

	/**
	 * Metodo acessador de logins
	 * 
	 * @return String login
	 */
	public String getName() {
		return login;
	}

	@Override
	public String toString() {
		return login;
	}

	@Override
	public boolean equals(Object login) {
		if (!(login instanceof Login)) {
			return false;
		}
		Login login2 = (Login) login;
		return login2.getName().equals(this.getName());
	}

	private boolean validaLogin(String login) {
		if (login != null && !login.equals(""))
			return true;
		return false;
	}

}
