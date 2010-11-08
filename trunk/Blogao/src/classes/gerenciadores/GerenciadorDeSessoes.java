package classes.gerenciadores;

import interfaces.Gerenciador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.DataInvalidaException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import classes.Sessao;
import classes.func.usuario.Perfil;
import classes.func.usuario.Usuario;
import enuns.Constantes2;

import persistencia.daos.SessoesDAO;

/**
 * Classe para gerenciamento de sessoes no blogao
 * 
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 */
public class GerenciadorDeSessoes implements Gerenciador {
	private List<Sessao> listaSessoes;

	private SessoesDAO sessoesDAO = SessoesDAO.getInstance();
	private GerenciadorDeDados gerenteDados;

	public GerenciadorDeSessoes(GerenciadorDeDados gerenciadorDeDados) {
		listaSessoes = new ArrayList<Sessao>();
		this.gerenteDados = gerenciadorDeDados;
	}

	/**
	 * Salva os dados.
	 */
	@Override
	public void saveData() throws PersistenceException, IOException {
		sessoesDAO.limparSessoes();
		Iterator<Sessao> it = iteradorSessao();
		while(it.hasNext()){
			sessoesDAO.criar(it.next());
		}
	}

	@Override
	/**
	 * Carrega os dados.
	 */
	public void loadData() {
		try {
			listaSessoes = sessoesDAO.recuperaSessoes();
		} catch (FileNotFoundException e) {
			listaSessoes = new ArrayList<Sessao>();
		}
	
	}

	@Override
	/**
	 * Remove todos os arquivos que eram persistentes.
	 */
	public void cleanPersistence() {
		sessoesDAO.limparSessoes();
		listaSessoes = new ArrayList<Sessao>();
	}

	/**
	 * Metodo que verifica se um usuario ja esta logado.
	 * 
	 * @param login
	 *            {@link String}
	 * @return {@link boolean}
	 * @throws PersistenceException
	 * @throws FileNotFoundException
	 * @throws ArgumentInvalidException
	 * @throws UserInvalidException
	 */
	public boolean isUserLogged(String login) throws Exception {
		Usuario user = getUserByLogin(login); //verifica se o usuario esta logado
		Iterator<Sessao> it = iteradorSessao();
		while(it.hasNext()){
			Sessao sessao = it.next();
			if (sessao.getLogin().equals(login)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Metodo que retorna a Sessao referente ao id.
	 * 
	 * @param idSessao
	 *            {@link String}
	 * @return {@link Sessao} Sessao
	 * @throws ArgumentInvalidException
	 */
	public Sessao getSessao(String idSessao) throws ArgumentInvalidException {
		Iterator<Sessao> it = iteradorSessao();
		while(it.hasNext()){
			Sessao sessao = it.next();
			if (sessao.getIdSessao().equals(idSessao)) {
				return sessao;
			}
		}
		throw new ArgumentInvalidException(Constantes2.SESSAO_INVALIDA.getName());
	}

	/**
	 * metodo que retorna a existencia de um logavel dado seu login.
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
		
		if (!sessaoExistente(idsessao))
			throw new ArgumentInvalidException(Constantes2.SESSAO_INVALIDA.getName());
		String retorno;
		String login = getLoginPorSessao(idsessao);
		if ("login".equals(atributo)) {
			return login;
		}
		Usuario us = getUserByLogin(login);
		Perfil perf = us.getPerfil();
		retorno = perf.getAtributo(atributo);
	
		return retorno;
	}

	/**
	 * Metodo que retorna o login referente a sessao passada como parametro.
	 * 
	 * @param idSessao
	 *            {@link String} a sessao da qual se procura o login
	 * @return login o login da sessao procurada
	 * @throws ArgumentInvalidException
	 *             caso a sessao nao exista
	 */
	public String getLoginPorSessao(String idSessao)
			throws ArgumentInvalidException {
		Sessao ses = getSessao(idSessao);
		return ses.getLogin();
	}

	/**
	 * Metodo que loga um usuario.
	 * 
	 * @param login
	 *            {@link String}
	 * @param senha
	 *            {@link String}
	 * @return id da Sessao {@link String}
	 * @throws Exception 
	 */
	public String logon(String login, String senha)
			throws Exception {
		try {
			String idSessao = String.valueOf(login.hashCode());
			if (isUserLogged(login)) {
				throw new ArgumentInvalidException(Constantes2.USUARIO_LOGADO.getName());
			}
			Usuario user = getUserByLogin(login);
			Sessao sessaoNova = new Sessao(idSessao, login);
	
			if (user.getSenha().equals(senha)) {
				listaSessoes.add(sessaoNova);
				return idSessao;
			} else {
				throw new PersistenceException(
						Constantes2.LOGIN_OU_SENHA_INVALIDO.getName());
			}
		} catch (PersistenceException e) {
			throw new PersistenceException(Constantes2.LOGIN_OU_SENHA_INVALIDO.getName());
		} catch (UserInvalidException e) {
			throw new ArgumentInvalidException(
					Constantes2.LOGIN_OU_SENHA_INVALIDO.getName());
		}
	
	}

	/**
	 * Metodo que desloga um usuario.
	 * 
	 * @param idSession
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException 
	 */
	public void logoff(String idSession) throws ArgumentInvalidException, PersistenceException {
		Sessao ses = getSessao(idSession);
		listaSessoes.remove(ses);		
	}
	

	
	/**
	 * Validador da Sessao.
	 * 
	 * @param sessionId
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 */
	public void validaSessao(String sessionId) throws ArgumentInvalidException {
		Iterator<Sessao> it = iteradorSessao();
		while(it.hasNext()){
			Sessao idSessao = it.next();
			if (idSessao.getIdSessao().equals(sessionId))
				return;
		}
		throw new ArgumentInvalidException(Constantes2.SESSAO_INVALIDA.getName());
	}

	private Usuario getUserByLogin(String login) throws UserInvalidException {
		return gerenteDados.getGerenteUsuarios().getUsuario(login);
	}

	/**
	 * Verificador de existencia de sessao.
	 * 
	 * @param idsessao
	 *            {@link String}
	 * @return booleano.
	 */
	private boolean sessaoExistente(String idsessao) {
		Iterator<Sessao> it = iteradorSessao();
		while(it.hasNext()){
			Sessao sessao = it.next();
			if (sessao.getIdSessao().equals(idsessao)) {
				return true;
			}
		}
		return false;
	}

	
	/**
	 * Iterador sobre a lista de Sessoes.
	 * @return Iterator<Sessao>
	 */
	public Iterator<Sessao> iteradorSessao(){
		return new Iterator<Sessao>() {
			private int cursor = 0;


			@Override
			public boolean hasNext() {
				while(cursor < listaSessoes.size()) {
					if(listaSessoes.get(cursor) instanceof Sessao)
						return true;
					cursor++;
				}				
				return false;
			}


			@Override
			public Sessao next() {
				try {
					Sessao b = listaSessoes.get(cursor);
					if(b instanceof Sessao) {
						cursor++;
						return b;
					}
					cursor++;
				} catch (NoSuchElementException e) {
					throw e;
				}
				return next();
			}


			@Override
			public void remove() {				
			}
		};
	}
}
