
package classes;

import interfaces.Logavel;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

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
	
	public boolean equals(Object login) {
		if (!(login instanceof Login)){
			return false;
		}
		Login login2 = (Login) login;
		return login2.getLogin().equals(this.login);
	}
	
	public String toString() {
		return login;
	}
	
	public void setLogin(String login) throws ArgumentInvalidException {
		if (!validaLogin(login)) {
			throw new ArgumentInvalidException("Login inv�lido");
		}
		this.login = login;
		
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
