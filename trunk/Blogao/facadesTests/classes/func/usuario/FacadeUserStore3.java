package classes.func.usuario;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.EmailsDAO;
import persistencia.daos.UsuariosDAO;
import classes.Email;
import classes.GerenciadorDeSessoes;
import classes.Login;
import classes.Senha;

/**
 * Facade de alteracao do perfil. Necessarios para os testes US3
 * 
 * @author Tiago
 * @author Rodolfo Marinho
 */
public class FacadeUserStore3 {
	private UsuariosDAO userDAO;
	private EmailsDAO mailDAO;
	private GerenciadorDeSessoes gerente = new GerenciadorDeSessoes();

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

	// TODO SETA TODAS AS VARIAVEIS DO PERFIL E TESTA-AS.
	public void changeProfileInformation(String idSessao, String atributo,
			String novoValor) throws PersistenceException,
			ArgumentInvalidException, IOException {
		try {
			String login = gerente.getLogin(idSessao);
			Usuario us = userDAO.recupera(login);
			if ("senha".equals(atributo)) {
				us.setSenha(new Senha(novoValor));
			} 
			else if ("login".equals(atributo)){
				us.setLogin(new Login(novoValor));
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

	// Armazenar no BD.
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws PersistenceException,
			ArgumentInvalidException, IOException {

		Login log = new Login(login);
		Senha sen = new Senha(senha);
		Email mail = new Email(email);
		Perfil perfil1;
		Usuario user1;

		perfil1 = new Perfil();
		perfil1.setNomeDeExibicao(nome_exibicao);
		perfil1.setEmail(mail);
		perfil1.setSexo(sexo);
		perfil1.setDataDeNascimento(dataNasc);
		perfil1.setEndereco(endereco);
		perfil1.setInteresses(interesses);
		perfil1.setQuemSouEu(quem_sou_eu);
		perfil1.setFilmesFavoritos(filmes);
		perfil1.setMusicasFavoritas(musicas);
		perfil1.setLivrosFavoritos(livros);

		user1 = new Usuario(log, sen, perfil1);

		mailDAO.criar(mail);
		userDAO.criar(user1);
	}

	// TODO METODO QUE DESLOGA O USUARIO.
	public void logoff() {
	}

	// TODO SALVA TODOS OS DADOS NO BD
	public void saveData() {
	}

}
