package classes.func.usuario;

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
			String musicas, String livros) {
		
		gerenteDados.getGerentePerfis().createProfile(login, senha,
				nome_exibicao, email, sexo, dataNasc, endereco, interesses,
				quem_sou_eu, filmes, musicas, livros);
	}

	public String logon(String login, String senha) {
		gerenteDados.getGerenteSessoes().logon(login, senha);
	}
	
	
	public String createBlog(String sessionId, String titulo, String descricao) {
		return gerenteDados.getGerenteBlogs().createBlog(sessionId, titulo, descricao);
	}
	
	//TODO
	public String createSubBlog(String sessionId, String blogPai, String titulo, String descricao) {
		
	}

	// sub blogs não entram na contabilização de blogs de usuários, ou seja, um subblog é encarado como uma seção de um blog
	public String getNumberOfBlogsByLogin(String login) {
		
	}

	//Considera apenas um nivel de subblogs.A flag passada indica se deve ser feita uma busca em subniveis
	public String getNumberOfSubBlogs(String blogId) {
		
	}

	public String getNumberOfAllSubBlogs(String blogId) {
		
	} 


	// Considera-se apenas um nível
	public String getSubBlog(String blogId, String index) {
		
	}


	public String createPost(String sessionId, String blogId, String titulo, String texto) {
		return gerenteDados.getGerentePosts().createPost(sessionId, blogId, titulo, texto);
	}


	//Retorna o numero de posts apenas do blog pai. 
	public String getNumberOfPosts(String blogId) {
		return gerenteDados.getGerenteBlogs().totalDePosts(blogId);
	}

	//Retorna o numero total de posts de um blog, Considerando todos os niveis

	public String getNumberOfAllPosts(String blogId) {
		
	}

	public String addComment(String sessionId, String postId, String texto) {
		gerenteDados.getGerenteComentarios().addComentario(sessionId, postId, texto);
	}
	
	public String addSubComment(String sessionId, String idComentario, String texto) {
		
	}

	public String getSubComment(String idComentario, String index) {
		
	}

	//Considera apenas um nivel de Comentario.A flag passada indica se deve ser feita uma busca em subniveis
	public String getNumberOfSubComments(String idComentario) {
		
	}
	
	//Retorna o numero total de subcomentários de um comentário, Considerando todos os niveis
	public String getNumberOfAllSubComments(String idComentario) {
		
	}
}
