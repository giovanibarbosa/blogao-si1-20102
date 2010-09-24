
package classes;

import interfaces.Constantes;
import ourExceptions.ArgumentInvalidException;

/**
 * @author Ana Clara Lacerda - anacls@lcc.ufcg.edu.br
 *
 */
public class Login {
	
	private String login;
	
	public Login(String login) throws ArgumentInvalidException {
		setLogin(login);
	}
	
	private boolean validaLogin(String login) {
		if (login != null && !login.equals(""))//&& login.matches("\\.+"))
            return true;
        return false;
	}
	
	public String getLogin() {
		return login;
	}
		
	public String toString() {
		return login;
	}
	
	public void setLogin(String login) throws ArgumentInvalidException {
		if (!validaLogin(login)) {
			throw new ArgumentInvalidException(Constantes.LOGIN_INVALIDO);
		}
		this.login = login;
		
	}
	
	@Override
	public boolean equals(Object login) {
		if (!(login instanceof Login)){
			return false;
		}
		Login login2 = (Login) login;
		return login2.getLogin().equals(this.getLogin());
	}

}
