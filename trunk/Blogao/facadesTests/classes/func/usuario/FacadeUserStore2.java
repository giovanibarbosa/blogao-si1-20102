package classes.func.usuario;


import java.io.FileNotFoundException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;

import classes.gerenciadores.GerenciadorDeDados;
import classes.gerenciadores.GerenciadorDeSessoes;

/**
 * Facade do Gerenciamento de Secao. Usado para os testes.
 * @author Tiago B.
 * @author Rodolfo Marinho
 */
public class FacadeUserStore2 {
	private GerenciadorDeDados gerenteDados = GerenciadorDeDados.getInstance();

	// CARREGA TODOS OS DADOS DO BD
	public void loadData() throws FileNotFoundException{
		gerenteDados.loadData();		
	}
	
	
	//METODO QUE LOGA O USUARIO
	public String logon(String login, String senha) throws PersistenceException,
				FileNotFoundException, ArgumentInvalidException, UserInvalidException {
		return gerenteDados.getGerenteSessoes().logon(login, senha);

	}
	
	//METODO QUE VERIFICA SE O USUARIO JA ESTA LOGADO
	public boolean isUserLogged(String login) throws PersistenceException,
				FileNotFoundException, ArgumentInvalidException, UserInvalidException {
		return gerenteDados.getGerenteSessoes().isUserLogged(login);				
	}
	
	/**
	 * metodo que retorna a existencia de um logavel dado seu login.
	 * @param log
	 * @return
	 * @throws ArgumentInvalidException 
	 * @throws PersistenceException 
	 * @throws UserInvalidException 
	 */

	//VERIFICAR SE ESSE 'ID' VAI SER UM DOUBLE MESMO
	public String getProfileInformationBySessionId(String id, String atributo) throws ArgumentInvalidException, PersistenceException, UserInvalidException{
		return gerenteDados.getGerenteSessoes().getProfileInformationBySessionId(id, atributo);

	}
	
	//METODO QUE DESLOGA O USUARIO.
	public void logoff(String idSession) throws ArgumentInvalidException{
		gerenteDados.getGerenteSessoes().logoff(idSession);
	}
	
	
}
