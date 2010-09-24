package classes.func.usuario;

import interfaces.Logavel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;

import persistencia.daos.BlogsDAO;
import persistencia.daos.EmailsDAO;
import persistencia.daos.PostsDAO;
import persistencia.daos.UsuariosDAO;

import classes.Login;
import classes.Senha;

/**
 * Facade do Gerenciamento de Secao. Usado para os testes.
 * @author Tiago B.
 * @author Rodolfo Marinho
 */
public class FacadeUserStore2 {
	private Usuario user;
	private Map<Integer, String> logados = new HashMap<Integer, String>();
	private UsuariosDAO userDAO = UsuariosDAO.getInstance();
	
	//CARREGA TODOS OS DADOS DO BD
	public void loadData(){
		userDAO = UsuariosDAO.getInstance();
	}
	
	
	//METODO QUE LOGA O USUARIO
	public int logon(String login, String senha) throws Exception{
		try {
			int idSessao = login.hashCode();
			Usuario us = userDAO.recupera(login);
			if (us.getSenha().equals(senha)) {
				if (logados.containsValue(login)) {
					throw new Exception("Usuário já logado");
				}
				logados.put(idSessao,login);
				return idSessao;
			} else {
				throw new PersistenceException("Login ou senha inválido");
			}
		} catch (PersistenceException e) {
			throw new Exception("Login ou senha inválido");
		}

	}
	
	//METODO QUE VERIFICA SE O USUARIO JA ESTA LOGADO
	public boolean isUserLogged(String login) throws PersistenceException, FileNotFoundException {
		try{
			userDAO.recupera(login);
			return logados.containsValue(login);
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
	public String getProfileInformationBySessionId(int id, String atributo) throws ArgumentInvalidException{
		if (!(logados.containsKey(id))) throw new ArgumentInvalidException("Sessão inválida");
		String retorno;
		String login = logados.get(id);
		if (atributo.equals("login")) return login;
		try {
			Usuario us = userDAO.recupera(login);
			Perfil perf = us.getPerfil();
			retorno = perf.getAtributo(atributo);
			
			if(retorno == null)
				return null;
			
		} catch (FileNotFoundException e) {
			return e.getMessage();
		} catch (PersistenceException e) {
			return e.getMessage();
		} catch (ArgumentInvalidException e) {
			throw e;
		}
		return retorno;
	}
	
	//METODO QUE DESLOGA O USUARIO.
	public void logoff(int idSession) throws ArgumentInvalidException{
		if (logados.remove(idSession) == null) throw new ArgumentInvalidException("Sessão inválida");
			
	}
	
	
}
