package facades;

import interfaces.Constantes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.DataInvalidaException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import classes.Sessao;
import classes.func.usuario.Perfil;
import classes.func.usuario.Usuario;
import classes.gerenciadores.NewGerenciadorDeBlogs;
import classes.gerenciadores.NewGerenciadorDeDados;
import classes.gerenciadores.NewGerenciadorDeSessoes;

public class FacadeSessao {
	private static FacadeSessao instance;
	private NewGerenciadorDeSessoes gerenteSessao;

	protected FacadeSessao() {
		gerenteSessao = new NewGerenciadorDeSessoes(NewGerenciadorDeDados.getInstance());
	}

	public static FacadeSessao getInstance() {
		if (instance == null) {
			instance = new FacadeSessao();
		}
		return instance;
	}
	/**
	 * Metodo que encapsula o metodo logon de gerenciador de sessao.
	 * @param login
	 * @param senha
	 * @return
	 * @throws PersistenceException 
	 * @throws ArgumentInvalidException 
	 * @throws FileNotFoundException 
	 */
	public String logon(String login, String senha) throws FileNotFoundException, ArgumentInvalidException, PersistenceException{
		return gerenteSessao.logon(login, senha);

	}
	
	/**
	*Metodo que encapsula o metodo isUserLogged de gerenciador de sessao.
	* 
	* @param login
	*            {@link String}
	* @return {@link boolean}
	* @throws PersistenceException
	* @throws FileNotFoundException
	* @throws ArgumentInvalidException
	* @throws UserInvalidException
	*/
	public boolean isUserLogged(String login) throws PersistenceException,
		FileNotFoundException, ArgumentInvalidException,
		UserInvalidException {
		return gerenteSessao.isUserLogged(login);
	
	}
	
	/**
	* Metodo que encapsula o metodo getProfileInformationBySessionId de gerenciador de sessao.
	* 
	* @param log
	*            {@link String}
	* @return {@link String} Informacoes do profile
	* @throws ArgumentInvalidException
	* @throws PersistenceException
	* @throws UserInvalidException
	* @throws DataInvalidaException 
	*/
	
	public String getProfileInformationBySessionId(String idsessao,
		String atributo) throws ArgumentInvalidException,
		PersistenceException, UserInvalidException, DataInvalidaException {
		return gerenteSessao.getProfileInformationBySessionId(idsessao, atributo);
	}
	
	/**
	* Metodo que encapsula o metodo getLoginPorSessao de gerenciador de sessao.
	* 
	* @param idSessao
	*            {@link String} a sessao da qual se procura o login
	* @return login o login da sessao procurada
	* @throws ArgumentInvalidException
	*             caso a sessao nao exista
	*/
	public String getLoginPorSessao(String idSessao)
		throws ArgumentInvalidException {
		return gerenteSessao.getLoginPorSessao(idSessao);
	}
	
	/**
	*Metodo que encapsula o metodo getSessao de gerenciador de sessao.
	* 
	* @param idSessao
	*            {@link String}
	* @return {@link Sessao} Sessao
	* @throws ArgumentInvalidException
	*/
	public Sessao getSessao(String idSessao) throws ArgumentInvalidException {
		return gerenteSessao.getSessao(idSessao);
	}
	
	
	/**
	* Metodo que encapsula o metodo logoff de gerenciador de sessao.
	* 
	* @param idSession
	*            {@link String}
	* @throws ArgumentInvalidException
	* @throws PersistenceException 
	*/
	public void logoff(String idSession) throws ArgumentInvalidException, PersistenceException {
		gerenteSessao.logoff(idSession);
	
	}
	
	
	/**
	 * Metodo que encapsula o metodo saveData de gerenciador de sessao.
	 * @throws PersistenceException
	 * @throws IOException
	 */
	public void saveData() throws PersistenceException, IOException {
		gerenteSessao.saveData();
	
	}
	
	/**
	 * Metodo que encapsula o metodo loadData de gerenciador de sessao.
	 */
	public void loadData() {
		gerenteSessao.loadData();
	
	}
	
	/**
	 * Metodo que encapsula o metodo cleanPersistence de gerenciador de sessao.
	 */
	public void cleanPersistence() {
		gerenteSessao.cleanPersistence();
	}
	
	/**
	* Metodo que encapsula o metodo validaSessao de gerenciador de sessao.
	* 
	* @param sessionId
	*            {@link String}
	* @throws ArgumentInvalidException
	*/
	public void validaSessao(String sessionId) throws ArgumentInvalidException {
		gerenteSessao.validaSessao(sessionId);
		
	}
}