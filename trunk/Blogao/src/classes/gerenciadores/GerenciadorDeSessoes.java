package classes.gerenciadores;

import interfaces.Constantes;
import interfaces.Gerenciador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.DataInvalidaException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import classes.Sessao;
import classes.func.usuario.Perfil;
import classes.func.usuario.Usuario;

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
			if (isUserLogged(login)) { //FIXME
				throw new ArgumentInvalidException(Constantes.USUARIO_LOGADO);
			}
			Usuario user = gerenteDados.getGerenteUsuarios().getUsuario(login);
			Sessao sessaoNova = new Sessao(idSessao, login);

			if (user.getSenha().equals(senha)) {
				listaSessoes.add(sessaoNova);
				return idSessao;
			} else {
				throw new PersistenceException(
						Constantes.LOGIN_OU_SENHA_INVALIDO);
			}
		} catch (PersistenceException e) {
			throw new PersistenceException(Constantes.LOGIN_OU_SENHA_INVALIDO);
		} catch (UserInvalidException e) {
			throw new ArgumentInvalidException(
					Constantes.LOGIN_OU_SENHA_INVALIDO);
		}

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
		//FIXME user não está sendo usado... o método passa em um teste, só que em outro não
		//quando ele é utilizado, com o if comentado abaixo, outros testes passam e outros não
		Usuario user = gerenteDados.getGerenteUsuarios().getUsuario(login);
		for (Sessao ses : listaSessoes) {
			//if (ses.getLogin().equals(user.getLogin()) //FIXME
			if (ses.getLogin().equals(login)) {
				return true;
			}
		}
		return false;

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
			throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);
		String retorno;
		String login = getLoginPorSessao(idsessao);
		if ("login".equals(atributo)) {
			return login;
		}
		Usuario us = gerenteDados.getGerenteUsuarios().getUsuario(login);
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
	 * Metodo que retorna a Sessao referente ao id.
	 * 
	 * @param idSessao
	 *            {@link String}
	 * @return {@link Sessao} Sessao
	 * @throws ArgumentInvalidException
	 */
	public Sessao getSessao(String idSessao) throws ArgumentInvalidException {
		for (Sessao ses : listaSessoes) {
			if (ses.getIdSessao().equals(idSessao)) {
				return ses;
			}
		}
		throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);
	}

	/**
	 * Verificador de existencia de sessao.
	 * 
	 * @param idsessao
	 *            {@link String}
	 * @return booleano.
	 */
	private boolean sessaoExistente(String idsessao) {
		for (Sessao ses : listaSessoes) {
			if (ses.getIdSessao().equals(idsessao)) {
				return true;
			}
		}
		return false;
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
//		/deleteSessionOnDB(ses);
		
	}
	
	private void deleteSessionOnDB(Sessao session) throws PersistenceException {
		sessoesDAO.deletar(session);
	}

	@Override
	/**
	 * Salva os dados.
	 */
	public void saveData() throws PersistenceException, IOException {
		sessoesDAO.limparSessoes();
		for (Sessao ses : listaSessoes) {
			sessoesDAO.criar(ses);
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
	 * Validador da Sessao.
	 * 
	 * @param sessionId
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 */
	public void validaSessao(String sessionId) throws ArgumentInvalidException {
		for (Sessao idSessao : listaSessoes) {
			if (idSessao.getIdSessao().equals(sessionId))
				return;
		}
		throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);
	}
	
//	public static void main(String[] args) {
//		
//		
//	}
}
