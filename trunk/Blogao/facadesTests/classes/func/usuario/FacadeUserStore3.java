package classes.func.usuario;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.UsuariosDAO;
import classes.GerenciadorDeSessoes;
import classes.Senha;

/**
 * Facade de alteracao do perfil. Necessarios para os testes US3
 * 
 * @author Tiago
 * @author Rodolfo Marinho
 */
public class FacadeUserStore3 {
	private UsuariosDAO userDAO;
	private GerenciadorDeSessoes gerente = new GerenciadorDeSessoes();

	// CARREGA TODOS OS DADOS DO BD
	public void loadData() {
		userDAO = UsuariosDAO.getInstance();
	}

	// METODO QUE LOGA O USUARIO
	public String logon(String login, String senha) throws FileNotFoundException,
			ArgumentInvalidException, PersistenceException {
		try {
			return gerente.logon(login, senha);
		} catch (PersistenceException e) {
			throw e;
		}
	}

	public String getProfileInformationBySessionId(String id, String atributo)
			throws ArgumentInvalidException {
		try {
			return gerente.getProfileInformationBySessionId(id, atributo);
		} catch (ArgumentInvalidException e) {
			throw e;
		}
	}

	// TODO SETA TODAS AS VARIAVEIS DO PERFIL E TESTA-AS.
	public void changeProfileInformation(String idSessao, String atributo,
			String novoValor) throws PersistenceException, ArgumentInvalidException, IOException{
		try {
			String login = gerente.getLogin(idSessao);
			Usuario us = userDAO.recupera(login);
			if ("senha".equals(atributo)){
				us.setSenha(new Senha(novoValor));
			}
			else{ 
				Perfil perfil = us.getPerfil();
				perfil.setAtributo(atributo, novoValor);
			}
			userDAO.atualizar(us);
			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (PersistenceException e) {
			throw e;
		} catch (ArgumentInvalidException e) {
			throw e;
		}

	}

	// TODO METODO QUE DESLOGA O USUARIO.
	public void logoff() {
	}

	// TODO SALVA TODOS OS DADOS NO BD
	public void saveData() {
	}

}
