package facadesUserStores;


import java.io.FileNotFoundException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;

import persistencia.daos.UsuariosDAO;

import classes.GerenciadorDeSessoes;

/**
 * Facade do Gerenciamento de Secao. Usado para os testes.
 * @author Tiago B.
 * @author Rodolfo Marinho
 */
public class FacadeUserStore2 {
	private GerenciadorDeSessoes gerente = new GerenciadorDeSessoes();
	private UsuariosDAO userDAO;
	
	//CARREGA TODOS OS DADOS DO BD
	public void loadData(){
		userDAO = UsuariosDAO.getInstance();
	}
	
	
	// TODO METODO QUE LOGA O USUARIO
	public String logon(String login, String senha) throws PersistenceException, FileNotFoundException, ArgumentInvalidException{
		try {
			return gerente.logon(login, senha);
		} catch (PersistenceException e){
			throw e;
		}

	}
	
	//METODO QUE VERIFICA SE O USUARIO JA ESTA LOGADO
	public boolean isUserLogged(String login) throws PersistenceException, FileNotFoundException {
		try{
			return gerente.isUserLogged(login);
		} catch (PersistenceException e){
			throw e;
		}
				
	}
	
	/**
	 * metodo que retorna a existencia de um logavel dado seu login.
	 * @param log
	 * @return
	 * @throws ArgumentInvalidException 
	 */

	//VERIFICAR SE ESSE 'ID' VAI SER UM DOUBLE MESMO
	public String getProfileInformationBySessionId(String id, String atributo) throws ArgumentInvalidException{
		try{
			return gerente.getProfileInformationBySessionId(id, atributo);
		} catch (ArgumentInvalidException e){
			throw e;
		}
	}
	
	//METODO QUE DESLOGA O USUARIO.
	public void logoff(String idSession) throws ArgumentInvalidException{
		try {
			gerente.logoff(idSession);
		} catch (ArgumentInvalidException e){
			throw e;
		}
	}
	
	
}
