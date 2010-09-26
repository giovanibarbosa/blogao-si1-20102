package classes.func.usuario;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import persistencia.daos.UsuariosDAO;
import classes.gerenciadores.GerenciadorDeDados;
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
	private GerenciadorDeDados gerenteDados = new GerenciadorDeDados();

	// CARREGA TODOS OS DADOS DO BD
	public void loadData() throws FileNotFoundException {
		gerenteDados.loadData();
	}

	// METODO QUE LOGA O USUARIO
	public String logon(String login, String senha)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException {
		return gerenteDados.getGerenteSessoes().logon(login, senha);

	}

	public String getProfileInformationBySessionId(String id, String atributo)
			throws ArgumentInvalidException, PersistenceException, UserInvalidException {
		return gerenteDados.getGerenteSessoes().getProfileInformationBySessionId(id, atributo);

	}

	// SETA TODAS AS VARIAVEIS DO PERFIL E TESTA-AS.
	public void changeProfileInformation(String idSessao, String atributo,
			String novoValor) throws Exception {

		gerenteDados.getGerentePerfis().changeProfileInformation(idSessao, atributo, novoValor);
		

	}

	//Armazenar no BD.
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception {		
	
		gerenteDados.getGerentePerfis().createProfile(login, senha, nome_exibicao, email, sexo,
				dataNasc, endereco, interesses, quem_sou_eu, filmes, musicas, livros);

	}

	//METODO QUE DESLOGA O USUARIO.
	public void logoff(String idSession) throws ArgumentInvalidException {
		gerenteDados.getGerenteSessoes().logoff(idSession);
	}

	//SALVA TODOS OS DADOS NO BD
	public void saveData() throws PersistenceException, IOException {
		gerenteDados.saveData();
	}

}
