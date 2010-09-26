package classes.gerenciadores;

import interfaces.Constantes;
import interfaces.Gerenciador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import classes.Comentario;
import classes.Sessao;
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
public class GerenciadorDeSessoes implements Gerenciador {
	private List<Sessao> listaSessoes;

	private SessoesDAO sessoesDAO = SessoesDAO.getInstance();
	private GerenciadorDeDados gerenteDados;

	public GerenciadorDeSessoes(GerenciadorDeDados gerenciadorDeDados) {
		listaSessoes = new ArrayList<Sessao>();
		this.gerenteDados = gerenciadorDeDados;
	}

	// METODO QUE LOGA O USUARIO
	public String logon(String login, String senha)
			throws ArgumentInvalidException, PersistenceException,
			FileNotFoundException {
		try {
			String idSessao = String.valueOf(login.hashCode());
			if (isUserLogged(login)) {
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
		} catch (ArgumentInvalidException e) {
			throw e;
		} catch (UserInvalidException e) {
			throw new ArgumentInvalidException(
					Constantes.LOGIN_OU_SENHA_INVALIDO);
		}

	}

	// METODO QUE VERIFICA SE O USUARIO JA ESTA LOGADO
	public boolean isUserLogged(String login) throws PersistenceException,
			FileNotFoundException, ArgumentInvalidException,
			UserInvalidException {
		Usuario user = gerenteDados.getGerenteUsuarios().getUsuario(login);
		for (Sessao ses : listaSessoes) {
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
	 * @return
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 * @throws UserInvalidException
	 */

	public String getProfileInformationBySessionId(String idsessao,
			String atributo) throws ArgumentInvalidException,
			PersistenceException, UserInvalidException {
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
	 *            a sessao da qual se procura o login
	 * @return login o login da sessao procurada
	 * @throws ArgumentInvalidException
	 *             caso a sessao nao exista
	 */
	public String getLoginPorSessao(String idSessao)
			throws ArgumentInvalidException {
		Sessao ses = getSessao(idSessao);
		return ses.getLogin();
	}

	public Sessao getSessao(String idSessao) throws ArgumentInvalidException {
		for (Sessao ses : listaSessoes) {
			if (ses.getIdSessao().equals(idSessao)) {
				return ses;
			}
		}
		throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);
	}

	private boolean sessaoExistente(String idsessao) {
		for (Sessao ses : listaSessoes) {
			if (ses.getIdSessao().equals(idsessao)) {
				return true;
			}
		}
		return false;
	}

	// METODO QUE DESLOGA O USUARIO.
	public void logoff(String idSession) throws ArgumentInvalidException {
		Sessao ses = getSessao(idSession);
		listaSessoes.remove(ses);
	}

	@Override
	public void saveData() throws PersistenceException, IOException {
		sessoesDAO.limparSessoes();
		for (Sessao ses : listaSessoes) {
			sessoesDAO.criar(ses);
		}

	}

	@Override
	public void loadData() {
		try {
			listaSessoes = sessoesDAO.recuperaSessoes();
		} catch (FileNotFoundException e) {
			listaSessoes = new ArrayList<Sessao>();
		}

	}

	@Override
	public void cleanPersistence() {
		sessoesDAO.limparSessoes();
	}
}
