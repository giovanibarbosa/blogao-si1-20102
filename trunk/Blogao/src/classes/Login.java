
package classes;

import java.io.UnsupportedEncodingException;

import ourExceptions.ArgumentInvalidException;

/**
 * @author Ana Clara Lacerda - anacls@lcc.ufcg.edu.br
 *
 */
public class Login {
	
	private String login;
	
	
	
	public Login(String login) throws Exception {
		setLogin(login);
	}
	
	//FIXME Verificar esse patterns.. 
	private boolean validaLogin(String login) {
		// verificar se tem numero minimo e/ou maximo de caracteres
		if (login != null && !login.equals("") )//&& login.matches("\\.+"))
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
	
	public void setLogin(String login) throws ArgumentInvalidException {
		if (!validaLogin(login)) {
			throw new ArgumentInvalidException("Login inválido");
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
	
//	public static void main(String[] args) throws Exception {
//		Login log = new Login("tiago");
//	}
	

}
