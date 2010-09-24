/**
 * 
 */
package interfaces;

import ourExceptions.ArgumentInvalidException;
import classes.*;

/**
 * @author Ana Clara Lacerda  - anacls@lcc.ufcg.edu.br
 *
 */
public interface Logavel {
	
	public Login getLogin();
	public Senha getSenha();
	public void setLogin(Login login) throws ArgumentInvalidException;
	public void setSenha(Senha senha);

}
