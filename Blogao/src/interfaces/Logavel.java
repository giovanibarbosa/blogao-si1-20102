/**
 * 
 */
package interfaces;

import ourExceptions.ArgumentInvalidException;
import classes.*;

/**
 * Interface de um objeto Logavel
 * @author Ana Clara Lacerda  - anacls@lcc.ufcg.edu.br
 *
 */
public interface Logavel {
	
	/**
	 * Metodo acessador de login
	 * @return {@link Login}
	 */
	public Login getLogin();
	
	/**
	 * Metodo acessador de Senha
	 * @return {@link Senha}
	 */
	public Senha getSenha();
	
	/**
	 * Metodo modificador de login
	 * @param {@link Login}
	 */
	public void setLogin(Login login) throws ArgumentInvalidException;
	
	/**
	 * Metodo modificador que seta uma nova senha
	 * @param {@link Senha}
	 */
	public void setSenha(Senha senha);

}
