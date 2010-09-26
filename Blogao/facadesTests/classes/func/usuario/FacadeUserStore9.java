package classes.func.usuario;

import interfaces.Gerenciador;

import java.io.FileNotFoundException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import classes.Comentario;
import classes.gerenciadores.GerenciadorDeBlogs;
import classes.gerenciadores.GerenciadorDeComentarios;
import classes.gerenciadores.GerenciadorDeDados;
import classes.gerenciadores.GerenciadorDePerfis;
import classes.gerenciadores.GerenciadorDePosts;
import classes.gerenciadores.GerenciadorDeSessoes;

public class FacadeUserStore9 {
	private GerenciadorDeSessoes gerenteSessoes;
	private GerenciadorDeComentarios gerenteComentarios;
	private GerenciadorDePerfis gerentePerfis;
	private GerenciadorDeBlogs gerenteBlogs;
	private GerenciadorDePosts gerentePosts;
	private GerenciadorDeDados gerenteDados = new GerenciadorDeDados();
	private Gerenciador[] gerenciadores= new Gerenciador[]{gerenteBlogs, gerenteComentarios, gerentePerfis, gerentePosts, gerenteSessoes};

	public FacadeUserStore9() {
		gerenteSessoes = new GerenciadorDeSessoes();
		gerenteComentarios = new GerenciadorDeComentarios(gerenteSessoes);
		gerentePerfis = new GerenciadorDePerfis();
		gerentePosts = new GerenciadorDePosts(gerenteSessoes, gerenteBlogs);
	}

	// CARREGA TODOS OS DADOS DO BD
	public void loadData() {
		gerenteDados.loadData(gerenciadores);
		
	}
	
	// METODO QUE LOGA O USUARIO
	public String logon(String login, String senha)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException {
		return gerenteSessoes.logon(login, senha);

	}
	
	//RETORNA O ID DO BLOG DADO O LOGIN DO USUARIO E O INDICE.
	public int getBlogByLogin(String login, int index) throws
				FileNotFoundException, PersistenceException {
		return gerenteBlogs.recuperaIdBlogPorLogin(login, index);

	}
	
	// TODO RETORNA O ID DO POST DADO O ID DO BLOG E O INDICE.
	public int getPost(String blogId, int index) throws NumberFormatException,
				FileNotFoundException, PersistenceException {
		return gerenteBlogs.recuperaIdDoPost(blogId, index);
	}
	
	//TODO RETORNA O COMENTARIO SEGUNDO O POST(PARAMENTRO) E SEU INDICE.
	public Comentario getComment(String postId, int index){
		return null;
	}
	
	//TODO RETORNA O TEXTO DO COMETARIO.
	public String getCommentText(String idComentario){
		return null;
	}
	
	//TODO RETORNA O NOME DO AUTOR DO COMENTARIO.
	public String getCommentAuthor(String idComentario){
		return null;
	}
	
	//TODO METODO QUE DELETA UM POST.
	public void deletePost(String sessionId, String postId){
		gerentePosts.deletePost(sessionId, postId);
	}
	
	//TODO RETORNA AS INFORMACOES DO POST.
	public void getPostInformation(String idPost,String atributo){
		
	}
	

	public void logoff(String idSessao) throws ArgumentInvalidException{
		gerenteSessoes.logoff(idSessao);
	}
	
	public void saveData(){
		gerenteDados.saveData(gerenciadores);
	}
}
