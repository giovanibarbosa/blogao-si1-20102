
package classes;

import interfaces.Constantes;
import ourExceptions.ArgumentInvalidException;

/**
 * Classe que inicializa um {@link Login}
 * @author Ana Clara Lacerda - anacls@lcc.ufcg.edu.br
 * @colaborator Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 *
 */
public class Login {
	
	private String login;
	
	
	/**
	 * Construtor do Login
	 * @param String login desejado
	 * @throws {@link ArgumentInvalidException} caso o login seja 
	 * nulo ou vazio
	 */
	public Login(String login) throws ArgumentInvalidException {
		setLogin(login);
	}
	
	/**
	 * Metodo modificador que seta um novo login
	 * @param {@link String} login
	 * @throws {@link ArgumentInvalidException} caso o login seja 
	 * nulo ou vazio
	 */
	public void setLogin(String login) throws ArgumentInvalidException {
		if (!validaLogin(login)) {
			throw new ArgumentInvalidException(Constantes.LOGIN_INVALIDO);
		}
		this.login = login;
		
	}

	/**
	 * Metodo acessador de logins
	 * @return String login
	 */
	public String getLogin() {
		return login;
	}
		
	
	@Override
	public String toString() {
		return login;
	}
	
	@Override
	public boolean equals(Object login) {
		if (!(login instanceof Login)){
			return false;
		}
		Login login2 = (Login) login;
		return login2.getLogin().equals(this.getLogin());
	}
	
	
	private boolean validaLogin(String login) {
		if (login != null && !login.equals(""))
            return true;
        return false;
	}

}
