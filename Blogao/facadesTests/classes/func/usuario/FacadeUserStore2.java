package classes.func.usuario;


import java.io.FileNotFoundException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;

import classes.gerenciadores.GerenciadorDeSessoes;

/**
 * Facade do Gerenciamento de Secao. Usado para os testes.
 * @author Tiago B.
 * @author Rodolfo Marinho
 */
public class FacadeUserStore2 {
	private GerenciadorDeSessoes gerente = new GerenciadorDeSessoes();
	
	// TODO CARREGA TODOS OS DADOS DO BD
	public void loadData(){
		
	}
	
	
	//METODO QUE LOGA O USUARIO
	public String logon(String login, String senha) throws PersistenceException, FileNotFoundException, ArgumentInvalidException{
		return gerente.logon(login, senha);


	}
	
	//METODO QUE VERIFICA SE O USUARIO JA ESTA LOGADO
	public boolean isUserLogged(String login) throws PersistenceException, FileNotFoundException {
		return gerente.isUserLogged(login);

				
	}
	
	/**
	 * metodo que retorna a existencia de um logavel dado seu login.
	 * @param log
	 * @return
	 * @throws ArgumentInvalidException 
	 */

	//VERIFICAR SE ESSE 'ID' VAI SER UM DOUBLE MESMO
	public String getProfileInformationBySessionId(String id, String atributo) throws ArgumentInvalidException{
		return gerente.getProfileInformationBySessionId(id, atributo);

	}
	
	//METODO QUE DESLOGA O USUARIO.
	public void logoff(String idSession) throws ArgumentInvalidException{
		gerente.logoff(idSession);
	}
	
	
}
