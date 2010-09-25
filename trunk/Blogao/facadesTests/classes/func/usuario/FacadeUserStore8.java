package classes.func.usuario;

import java.io.FileNotFoundException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.BlogsDAO;
import persistencia.daos.ComentariosDAO;
import persistencia.daos.EmailsDAO;
import persistencia.daos.PostsDAO;
import persistencia.daos.UsuariosDAO;
import classes.Blog;
import classes.Comentario;
import classes.Email;
import classes.gerenciadores.GerenciadorDeBlogs;
import classes.gerenciadores.GerenciadorDePerfis;
import classes.gerenciadores.GerenciadorDePosts;
import classes.gerenciadores.GerenciadorDeSessoes;
import classes.gerenciadores.GerenciadorDeComentarios;
import classes.Login;
import classes.Senha;

/**
 * Classe para testes do us8
 * 
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * 
 */

public class FacadeUserStore8 {
	private Perfil perfil1;
	private Usuario user1;
	private GerenciadorDeSessoes gerenteSessoes;
	private GerenciadorDeComentarios gerenteComentarios;
	private GerenciadorDePerfis gerentePerfis;
	private GerenciadorDeBlogs gerenteBlogs;
	private GerenciadorDePosts gerentePosts;
	private Blog blog;

	private UsuariosDAO userDAO;
	private BlogsDAO blogsDAO;
	private EmailsDAO emailsDAO;
	private PostsDAO postsDAO;
	private ComentariosDAO comentsDAO;

	public FacadeUserStore8() {
		gerenteSessoes = new GerenciadorDeSessoes();
		gerenteSessoes.loadData();
		gerenteComentarios = new GerenciadorDeComentarios(gerenteSessoes);
		gerentePerfis = new GerenciadorDePerfis();
		gerentePosts = new GerenciadorDePosts(gerenteSessoes, gerenteBlogs);
	}

	// CARREGA TODOS OS DADOS DO BD
	public void loadData() {
		gerenteComentarios.loadData();
		gerentePosts.loadData();
	}

	// TODO Armazenar no BD.
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception {

		gerentePerfis.createProfile(login, senha, nome_exibicao, email, sexo,
				dataNasc, endereco, interesses, quem_sou_eu, filmes, musicas,
				livros);
	}

	// METODO QUE LOGA O USUARIO
	public String logon(String login, String senha)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException {
		try {
			return gerenteSessoes.logon(login, senha);
		} catch (PersistenceException e) {
			throw e;
		}
	}

	// TODO RETORNA O ID DO BLOG DADO O LOGIN DO USUARIO E O INDICE.
	public String getBlogByLogin(String login, int index) {
		return null;

	}

	// TODO RETORNA O ID DO POST DADO O ID DO BLOG E O INDICE.
	public String getPost(String blogId, int index) {
		return null;
	}

	// TODO ADICIONA UM COMENTARIO. o RETORNO DO METODO E O ID DO COMENTARIO
	public String addComment(String sessionId, String postId, String texto) {
		return null;
	}

	// TODO RETORNA O NUMERO DE COMETRAIOS DO POST.
	public int getNumberOfComments(String postId) throws FileNotFoundException, PersistenceException {
		return gerentePosts.getNumberOfComments(postId);
	}

	// TODO RETORNA O COMENTARIO SEGUNDO O POST(PARAMENTRO) E SEU INDICE.
	public Comentario getComment(String postId, int index) {
		return null;
	}

	// TODO RETORNA O TEXTO DO COMETARIO.
	public String getCommentText(String idComentario) {
		return null;
	}

	// TODO RETORNA O NOME DO AUTOR DO COMENTARIO.
	public String getCommentAuthor(String idComentario) {
		return null;
	}
}
