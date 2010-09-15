
package classes;

/**
 * @author Ana Clara Lacerda - anacls@lcc.ufcg.edu.br
 *
 */
public class Login {
	
	private String login;
	
	public Login(String login) {
		this.login = login;
	}
	
	private boolean validaLogin(String login) {
		// verificar se tem numero minimo e/ou maximo de caracteres
		if (login != null && !login.equals("") && login.matches("\\.+"))
            return true;
        return false;
	}
	
	public String getLogin() {
		return login;
	}
	
	public boolean equals(Login login) {
		return login.getLogin().equals(this.login);
	}
	
	public String toString() {
		return login;
	}
	

}
