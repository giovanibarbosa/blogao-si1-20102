package classes.func.usuario;

import java.io.FileNotFoundException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.UsuariosDAO;
import classes.gerenciadores.GerenciadorDePerfis;
import classes.gerenciadores.GerenciadorDeSessoes;
import classes.Login;
import classes.Senha;

/**
 * Facade de alteracao do perfil. Necessarios para os testes US3
 * 
 * @author Rodolfo Marinho
 * 
 */
public class FacadeUserStore3 {
	private UsuariosDAO userDAO;
	private GerenciadorDeSessoes gerente = new GerenciadorDeSessoes();
	private GerenciadorDePerfis gerenteDoPerfil = new GerenciadorDePerfis();

	// CARREGA TODOS OS DADOS DO BD
	public void loadData() {
		userDAO = UsuariosDAO.getInstance();
	}

	// METODO QUE LOGA O USUARIO
	public String logon(String login, String senha)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException {
		return gerente.logon(login, senha);

	}

	public String getProfileInformationBySessionId(String id, String atributo)
			throws ArgumentInvalidException {
		return gerente.getProfileInformationBySessionId(id, atributo);

	}

	// SETA TODAS AS VARIAVEIS DO PERFIL E TESTA-AS.
	public void changeProfileInformation(String idSessao, String atributo,
			String novoValor) throws Exception {

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

	}

	//Armazenar no BD.
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception {		
	
		gerenteDoPerfil.createProfile(login, senha, nome_exibicao, email, sexo,
				dataNasc, endereco, interesses, quem_sou_eu, filmes, musicas, livros);

	}

	// TODO METODO QUE DESLOGA O USUARIO.
	public void logoff(String idSession) throws ArgumentInvalidException {
		gerente.logoff(idSession);
	}

	// TODO SALVA TODOS OS DADOS NO BD
	public void saveData() {
	}

}
