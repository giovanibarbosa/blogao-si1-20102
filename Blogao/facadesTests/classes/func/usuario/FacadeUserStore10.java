package classes.func.usuario;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import classes.gerenciadores.GerenciadorDeBlogs;
import classes.gerenciadores.GerenciadorDeComentarios;
import classes.gerenciadores.GerenciadorDeDados;
import classes.gerenciadores.GerenciadorDePerfis;
import classes.gerenciadores.GerenciadorDePosts;
import classes.gerenciadores.GerenciadorDeSessoes;

public class FacadeUserStore10 {
	
	private GerenciadorDeSessoes gerenteSessoes;
	private GerenciadorDeComentarios gerenteComentarios;
	private GerenciadorDePerfis gerentePerfis;
	private GerenciadorDeBlogs gerenteBlogs;
	private GerenciadorDePosts gerentePosts;
	private GerenciadorDeDados gerenteDados = new GerenciadorDeDados();

	public FacadeUserStore10() {
		gerenteSessoes = new GerenciadorDeSessoes();
		gerenteComentarios = new GerenciadorDeComentarios();
		gerentePerfis = new GerenciadorDePerfis();
		gerentePosts = new GerenciadorDePosts();
	}
	
	public void loadData() {
		gerenteDados.loadData();
	}


	//Efetua login dos usuários
	public String logon(String login, String senha) throws FileNotFoundException, ArgumentInvalidException,
	PersistenceException {
		return gerenteSessoes.logon(login, senha);
	}

	//RETORNA O ID DO BLOG DADO O LOGIN DO USUARIO E O INDICE.
	public String getBlogByLogin(String login, int index) throws
				FileNotFoundException, PersistenceException {
		return gerenteBlogs.recuperaIdBlogPorLogin(login, index);

	}

	public String createPost(String sessionId, String blogId, String titulo, String texto) {
		return gerentePosts.createPost(sessionId, blogId, titulo, texto);
	}

	public void deleteBlog(String sessionId, String blogId) {
		gerenteBlogs.deleteBlog(sessionId, blogId);
	}

	//Validação: os posts devem ser deletados
	public String getPostInformation(String id, String atributo) {
		return gerentePosts.informacaoDoPost(id, atributo);
	}

	//Desloga usuarios
	public void logoff(String sessionId) {
		gerenteSessoes.logoff(idSessao);
	}

	//Salva os dados de forma permanente
	public void saveData() throws PersistenceException, IOException {
		gerenteDados.saveData();
	}
}
