package classes.func.usuario;

import java.io.IOException;

import ourExceptions.PersistenceException;
import classes.gerenciadores.GerenciadorDeBlogs;
import classes.gerenciadores.GerenciadorDeComentarios;
import classes.gerenciadores.GerenciadorDeDados;
import classes.gerenciadores.GerenciadorDePerfis;
import classes.gerenciadores.GerenciadorDePosts;
import classes.gerenciadores.GerenciadorDeSessoes;

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
			String musicas, String livros) {
		
		gerenteDados.getGerentePerfis().createProfile(login, senha,
				nome_exibicao, email, sexo, dataNasc, endereco, interesses,
				quem_sou_eu, filmes, musicas, livros);
	}

	public String logon(String login, String senha) {
		gerenteDados.getGerenteSessoes().logon(login, senha);
	}

	public String getNumberOfAnnouncements(String sessionId) {

	}

	public String createBlog(String sessionId, String titulo, String descricao) {
		return gerenteDados.getGerenteBlogs().createBlog(sessionId, titulo, descricao);
	}

	public String createPost(String sessionId, String blogId, String titulo,
			String texto) {
		return gerenteDados.getGerentePosts().createPost(sessionId, blogId, titulo, texto);
	}

	// Adiciona um notificador para novos posts
	public void addPostAnnouncements(String sessionId, String blogId) {

	}

	public String getAnnouncement(String sessionId, String index) {

	}

	public String getPostJustCreated(String announcementId) {

	}

	public void deleteAnnouncement(String sessionId, String announcementId) {

	}

	public String getNumberOfAnnouncements(String sessionId) {

	}

	// Desloga usuarios
	public void logoff(String sessionId) {
		gerenteDados.getGerenteSessoes().logoff(sessionId);
	}

	// Salva os dados de forma permanente
	public void saveData() throws PersistenceException, IOException {
		gerenteDados.saveData();
	}

}
