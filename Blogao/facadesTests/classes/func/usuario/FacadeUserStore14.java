package classes.func.usuario;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import classes.gerenciadores.GerenciadorDeDados;

/**
 * 
 * @author Giovani Barbosa
 *
 */
public class FacadeUserStore14 {
	private GerenciadorDeDados gerenteDados = new GerenciadorDeDados();
	
	//Limpa quaisquer dados pre-existentes
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
	
	
	public String createBlog(String sessionId, String titulo, String descricao) throws ArgumentInvalidException, PersistenceException, IOException, UserInvalidException {
		return gerenteDados.getGerenteBlogs().createBlog(sessionId, titulo, descricao);
	}
	
	public String createSubBlog(String sessionId, String blogPai, String titulo, String descricao) throws ArgumentInvalidException, PersistenceException, IOException, UserInvalidException {
		return gerenteDados.getGerenteBlogs().createSubBlog(sessionId, blogPai, titulo, descricao);
		
	}

	// sub blogs não entram na contabilização de blogs de usuários, ou seja, um subblog é encarado como uma seção de um blog
	public int getNumberOfBlogsByLogin(String login) throws UserInvalidException {
		return gerenteDados.getGerenteBlogs().getNumberOfBlogsByLogin(login);
		
	}
	
	public int getNumberOfBlogsBySessionId(String idSession) throws UserInvalidException, ArgumentInvalidException{
		return gerenteDados.getGerenteBlogs().getNumberOfBlogsBySessionId(idSession);
	}

	//Considera apenas um nivel de subblogs.A flag passada indica se deve ser feita uma busca em subniveis
	public int getNumberOfSubBlogs(String blogId) throws ArgumentInvalidException {
		return gerenteDados.getGerenteBlogs().getNumberOfSubBlogs(blogId);
		
	}

	public int getNumberOfAllSubBlogs(String blogId) throws ArgumentInvalidException {
		return gerenteDados.getGerenteBlogs().getNumberOfAllSubBlogs(blogId);
		
	} 


	// Considera-se apenas um nível
	public String getSubBlog(String blogId, int index) throws ArgumentInvalidException {
		return gerenteDados.getGerenteBlogs().getSubBlog(blogId, index);
		
	}


	public String createPost(String sessionId, String blogId, String titulo, String texto) throws IOException, ArgumentInvalidException, PersistenceException, UserInvalidException {
		return gerenteDados.getGerentePosts().createPost(sessionId, blogId, titulo, texto);
	}


	//Retorna o numero de posts apenas do blog pai. 
	public int getNumberOfPosts(String blogId) throws FileNotFoundException, PersistenceException, ArgumentInvalidException {
		return gerenteDados.getGerenteBlogs().totalDePosts(blogId);
	}

	//Retorna o numero total de posts de um blog, Considerando todos os niveis

	public int getNumberOfAllPosts(String blogId) throws ArgumentInvalidException {
		return gerenteDados.getGerenteBlogs().getNumberOfAllPosts(blogId);
		
	}

	public String addComment(String sessionId, String postId, String texto) throws ArgumentInvalidException, UserInvalidException, PersistenceException {
		return gerenteDados.getGerenteComentarios().addComentario(sessionId, postId, texto);
	}
	
	public String addSubComment(String sessionId, String idComentario, String texto) {
		return null;
		
	}

	public String getSubComment(String idComentario, String index) {
		return gerenteDados.getGerenteBlogs().getSubComment(idComentario, index);
		
	}

	//Considera apenas um nivel de Comentario.A flag passada indica se deve ser feita uma busca em subniveis
	public String getNumberOfSubComments(String idComentario) {
		return null;
		
	}
	
	//Retorna o numero total de subcomentários de um comentário, Considerando todos os niveis
	public String getNumberOfAllSubComments(String idComentario) {
		return null;
		
	}
}
