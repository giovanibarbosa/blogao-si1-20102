package classes.gerenciadores;

import interfaces.Constantes;
import interfaces.Gerenciador;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import classes.Comentario;
import classes.func.usuario.Perfil;
import classes.func.usuario.Usuario;

import persistencia.daos.SessoesDAO;
import persistencia.daos.UsuariosDAO;

/**
 * Classe para gerenciamento de sessoes no blogao
 * 
 * @author Rodolfo Marinho
 * 
 */
public class GerenciadorDeSessoes implements Gerenciador{
	private Map<String, String> logados;
	private UsuariosDAO userDAO = UsuariosDAO.getInstance();
	private SessoesDAO sessoesDAO = SessoesDAO.getInstance();
	private List<String> listaSessoes;

	public GerenciadorDeSessoes() {
		logados = new HashMap<String, String>();
		listaSessoes = new ArrayList<String>();
	}

	// METODO QUE LOGA O USUARIO
	public String logon(String login, String senha)
			throws ArgumentInvalidException, PersistenceException,
			FileNotFoundException {
		try {
			String idSessao = String.valueOf(login.hashCode());
			Usuario us = userDAO.recupera(login);
			if (us.getSenha().equals(senha)) {
				if (logados.containsValue(login)) {
					throw new ArgumentInvalidException(Constantes.USUARIO_LOGADO);
				}
				logados.put(idSessao, login);
				return idSessao;
			} else {
				throw new PersistenceException(Constantes.LOGIN_OU_SENHA_INVALIDO);
			}
		} catch (PersistenceException e) {
			throw new PersistenceException(Constantes.LOGIN_OU_SENHA_INVALIDO);
		}

	}

	// METODO QUE VERIFICA SE O USUARIO JA ESTA LOGADO
	public boolean isUserLogged(String login) throws PersistenceException,
			FileNotFoundException {
		try {
			userDAO.recupera(login);
			return logados.containsValue(login);
		} catch (PersistenceException e) {
			throw e;
		}

	}

	/**
	 * metodo que retorna a existencia de um logavel dado seu login.
	 * 
	 * @param log
	 * @return
	 * @throws ArgumentInvalidException
	 */

	public String getProfileInformationBySessionId(String id, String atributo)
			throws ArgumentInvalidException {
		if (!(logados.containsKey(id)))
			throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);
		String retorno;
		String login = logados.get(id);
		if (atributo.equals("login"))
			return login;
		try {
			Usuario us = userDAO.recupera(login);
			Perfil perf = us.getPerfil();
			retorno = perf.getAtributo(atributo);

			if (retorno == null)
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

	// METODO QUE DESLOGA O USUARIO.
	public void logoff(String idSession) throws ArgumentInvalidException {
		if (logados.remove(idSession) == null)
			throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);

	}

	/**
	 * Metodo que retorna o login referente a sessao passada como parametro.
	 * 
	 * @param idSessao
	 * 					a sessao da qual se procura o login
	 * @return login
	 * 					o login da sessao procurada
	 * @throws ArgumentInvalidException
	 * 					caso a sessao nao exista
	 */
	public String getLogin(String idSessao) throws ArgumentInvalidException {
		String login = logados.get(idSessao);
		if (login == null) {
			throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);
		}
		return login;
	}
	
	public Map<String, String> getLogados() {
		return logados;
	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		
	}
	

	/**
	 * @return the listaSessoes
	 */
	public List<String> getListaSessoes() {
		return listaSessoes;
	}

	/**
	 * @param listaSessoes the listaSessoes to set
	 */
	public void setListaSessoes(List<String> listaSessoes) {
		this.listaSessoes = listaSessoes;
	}

	@Override
	public void loadData() {
		try {
			listaSessoes = sessoesDAO.recuperaSessoes();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			listaSessoes = new ArrayList<String>();
		}

	}

	@Override
	public void cleanPersistence() {
		// TODO Auto-generated method stub
		
	}
}
