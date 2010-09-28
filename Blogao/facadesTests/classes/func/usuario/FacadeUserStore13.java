package classes.func.usuario;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import classes.gerenciadores.GerenciadorDeBlogs;
import classes.gerenciadores.GerenciadorDeComentarios;
import classes.gerenciadores.GerenciadorDeDados;
import classes.gerenciadores.GerenciadorDePerfis;
import classes.gerenciadores.GerenciadorDePosts;
import classes.gerenciadores.GerenciadorDeSessoes;

/**
 * 
 * @author Giovani Barbosa
 *
 */
public class FacadeUserStore13 {
	private GerenciadorDeDados gerenteDados = new GerenciadorDeDados();

	// Limpa quaisquer dados pre-existentes
	public void cleanPersistence() {
		gerenteDados.cleanPersistence();
	}

	// Cria tres usuários
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception {
		
		gerenteDados.getGerentePerfis().createProfile(login, senha,
				nome_exibicao, email, sexo, dataNasc, endereco, interesses,
				quem_sou_eu, filmes, musicas, livros);
	}

	public String logon(String login, String senha) throws FileNotFoundException, ArgumentInvalidException, PersistenceException {
		return gerenteDados.getGerenteSessoes().logon(login, senha);
	}

	public String getNumberOfAnnouncements(String sessionId) {
		return gerenteDados.getGerenciadorDeUsuarios().getNumberOfAnnouncements(sessionId);

	}

	public String createBlog(String sessionId, String titulo, String descricao) throws ArgumentInvalidException, PersistenceException, IOException, UserInvalidException {
		return gerenteDados.getGerenteBlogs().createBlog(sessionId, titulo, descricao);
	}

	public String createPost(String sessionId, String blogId, String titulo,
			String texto) throws IOException, ArgumentInvalidException, PersistenceException, UserInvalidException {
		return gerenteDados.getGerentePosts().createPost(sessionId, blogId, titulo, texto);
	}

//	// Adiciona um notificador para novos posts
//	public void addPostAnnouncements(String sessionId, String blogId) {
//		gerenteDados.getGerenciadorDeUsuarios().addPostAnnouncement(sessionId, blogId);
//	}

	public String getAnnouncement(String sessionId, String index) {
		return null;

	}

	public String getPostJustCreated(String announcementId) {
		return null;

	}

	public void deleteAnnouncement(String sessionId, String announcementId) {

	}

	// Desloga usuarios
	public void logoff(String sessionId) throws ArgumentInvalidException {
		gerenteDados.getGerenteSessoes().logoff(sessionId);
	}

	// Salva os dados de forma permanente
	public void saveData() throws PersistenceException, IOException {
		gerenteDados.saveData();
	}

}