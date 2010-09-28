package classes.func.usuario;

import java.io.IOException;

import ourExceptions.PersistenceException;
import classes.gerenciadores.GerenciadorDeDados;

/**
 * Facade do Perfil. Utilizada para chamar os metodos necessarios para os
 * testes.
 * 
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 * @colaborator Rodolfo Marinho - rodolfo@lcc.ufcg.edu.br
 * @colaborator Giovani Barbosa - giovanibarbosa (at) gmail (dot) com
 * 
 */
public class FacadeUserStore1 {

	private GerenciadorDeDados gerenteDados = new GerenciadorDeDados();
	
	//APAGAR OS DADOS SALVOS
	public void cleanPersistence() {
		gerenteDados.cleanPersistence();
	}

	//SALVA TODOS OS DADOS NO BD
	public void saveData() throws PersistenceException, IOException {
		gerenteDados.saveData();
	}

	//FAZER ESTE METODO
	public String getProfileInformation(String login, String atributo) throws Exception {
		return gerenteDados.getGerentePerfis().getProfileInformation(login, atributo);	
	}

	//Armazenar no BD.
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception {
		
		gerenteDados.getGerentePerfis().createProfile(login, senha, nome_exibicao, email, sexo,
					dataNasc, endereco, interesses, quem_sou_eu, filmes, musicas, livros);
	}

}
