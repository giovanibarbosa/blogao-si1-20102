package facadesUserStores;

import java.io.FileNotFoundException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.EmailsDAO;
import persistencia.daos.UsuariosDAO;
import classes.GerenciadorDePerfis;
import classes.GerenciadorDeSessoes;
import classes.Login;
import classes.Senha;
import classes.func.usuario.Perfil;
import classes.func.usuario.Usuario;

/**
 * Facade de alteracao do perfil. Necessarios para os testes US3
 * 
 * @author Rodolfo Marinho
 * 
 */
public class FacadeUserStore3 {
	private UsuariosDAO userDAO;
	private EmailsDAO mailDAO;
	private GerenciadorDeSessoes gerente = new GerenciadorDeSessoes();
	private GerenciadorDePerfis gerenteDoPerfil = new GerenciadorDePerfis();

	// CARREGA TODOS OS DADOS DO BD
	public void loadData() {
		userDAO = UsuariosDAO.getInstance();
		mailDAO = EmailsDAO.getInstance();
	}

	// METODO QUE LOGA O USUARIO
	public String logon(String login, String senha)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException {
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

	// SETA TODAS AS VARIAVEIS DO PERFIL E TESTA-AS.
	public void changeProfileInformation(String idSessao, String atributo,
			String novoValor) throws Exception {

		try {
			String login = gerente.getLogin(idSessao);
			Usuario us = userDAO.recupera(login);
			if ("senha".equals(atributo)) {
				us.setSenha(new Senha(novoValor));
			} else if ("login".equals(atributo)) {
				Usuario usTemp = userDAO.recupera(login);
				us.setLogin(new Login(novoValor));
				userDAO.criar(us);
				userDAO.deletar(usTemp);

			}

			else {
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

	//Armazenar no BD.
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception {
		
		try {
			gerenteDoPerfil.createProfile(login, senha, nome_exibicao, email, sexo,
					dataNasc, endereco, interesses, quem_sou_eu, filmes, musicas, livros);
			
		} catch (Exception e) {
			throw e;
		}
	}

	// TODO METODO QUE DESLOGA O USUARIO.
	public void logoff(String idSession) throws ArgumentInvalidException {
		gerente.logoff(idSession);
	}

	// TODO SALVA TODOS OS DADOS NO BD
	public void saveData() {
	}

}
