
package classes;

import interfaces.Logavel;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import persistencia.daos.LogaveisDAO;

/**
 * @author Ana Clara Lacerda - anacls@lcc.ufcg.edu.br
 *
 */
public class Login {
	
	private String login;
	private LogaveisDAO daoLogavel = LogaveisDAO.getInstance();
	
	public Login(String login) throws Exception {
		setLogin(login);
	}
	
	private boolean validaLogin(String login) {
		// verificar se tem numero minimo e/ou maximo de caracteres
		if (login != null && !login.equals(""))//&& login.matches("\\.+"))
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
	
	public void setLogin(String login) throws Exception {
		if (!validaLogin(login)) {
			throw new ArgumentInvalidException("Login inválido");
		}
		else if (!verificaExistenciaLogin(login)) {
			throw new ArgumentInvalidException("Login Existente");
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
	
	public boolean verificaExistenciaLogin(String login) throws FileNotFoundException {
		List<Logavel> listaLogins = daoLogavel.recuperaLogaveis();	
		
		for (Logavel log : listaLogins) {
			if (log.getLogin().getLogin().equals(login))
				return false;			
		}		
		return true;
	}
	
	

}
