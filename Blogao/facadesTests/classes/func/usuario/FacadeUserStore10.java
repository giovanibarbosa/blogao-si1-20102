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

public class FacadeUserStore10 {
	private GerenciadorDeDados gerenteDados = new GerenciadorDeDados();
	
	public void loadData() throws FileNotFoundException {
		gerenteDados.loadData();
	}


	//Efetua login dos usuários
	public String logon(String login, String senha) throws FileNotFoundException, ArgumentInvalidException,
	PersistenceException {
		return gerenteDados.getGerenteSessoes().logon(login, senha);
	}

	//RETORNA O ID DO BLOG DADO O LOGIN DO USUARIO E O INDICE.
	public String getBlogByLogin(String login, int index) throws
				FileNotFoundException, PersistenceException, UserInvalidException {
		return gerenteDados.getGerenteBlogs().recuperaIdBlogPorLogin(login, index);

	}

	public String createPost(String sessionId, String blogId, String titulo, String texto) throws IOException, ArgumentInvalidException, PersistenceException, UserInvalidException {
		return gerenteDados.getGerentePosts().createPost(sessionId, blogId, titulo, texto);
	}

	public void deleteBlog(String sessionId, String blogId) {
		gerenteDados.getGerenteBlogs().deleteBlog(sessionId, blogId);
	}

	//Validação: os posts devem ser deletados
	public String getPostInformation(String id, String atributo) throws FileNotFoundException, ArgumentInvalidException, PersistenceException {
		return gerenteDados.getGerentePosts().informacaoDoPost(id, atributo);
	}

	//Desloga usuarios
	public void logoff(String sessionId) throws ArgumentInvalidException {
		gerenteDados.getGerenteSessoes().logoff(sessionId);
	}

	//Salva os dados de forma permanente
	public void saveData() throws PersistenceException, IOException {
		gerenteDados.saveData();
	}
}
