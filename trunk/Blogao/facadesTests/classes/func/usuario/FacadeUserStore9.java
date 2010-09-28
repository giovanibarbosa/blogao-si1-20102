package classes.func.usuario;

import interfaces.Gerenciador;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import classes.Comentario;
import classes.gerenciadores.GerenciadorDeDados;

public class FacadeUserStore9 {
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
	
	//RETORNA O ID DO BLOG DADO O LOGIN DO USUARIO E O INDICE.
	public String getBlogByLogin(String login, int index) throws
				FileNotFoundException, PersistenceException, UserInvalidException, ArgumentInvalidException {
		return gerenteDados.getGerenteBlogs().recuperaIdBlogPorLogin(login, index);

	}
	
	//RETORNA O ID DO POST DADO O ID DO BLOG E O INDICE.
	public String getPost(String blogId, int index) throws NumberFormatException,
				FileNotFoundException, PersistenceException, ArgumentInvalidException {
		return gerenteDados.getGerenteBlogs().recuperaIdDoPost(blogId, index);
	}
	
	//RETORNA O COMENTARIO SEGUNDO O POST(PARAMENTRO) E SEU INDICE.
	public String getComment(String postId, int index) throws PersistenceException{
		return gerenteDados.getGerentePosts().getPostPorId(postId).getListaComentarios().get(index).getId();
	}
	
	//RETORNA O TEXTO DO COMETARIO.
	public String getCommentText(String idComentario) throws ArgumentInvalidException{
		return gerenteDados.getGerenteComentarios().getTextoComentario(idComentario);
	}
	
	//RETORNA O NOME DO AUTOR DO COMENTARIO.
	public String getCommentAuthor(String idComentario) throws FileNotFoundException, ArgumentInvalidException, PersistenceException{
		return gerenteDados.getGerenteComentarios().getCommentAuthor(idComentario);
	}
	
	//METODO QUE DELETA UM POST.
	public void deletePost(String sessionId, String postId) throws ArgumentInvalidException, PersistenceException, IOException{
		gerenteDados.getGerentePosts().validaPostId(postId, sessionId);
		gerenteDados.getGerentePosts().removePost(postId);
		
	}
	
	//TODO RETORNA AS INFORMACOES DO POST.
	public String getPostInformation(String idPost,String atributo) throws ArgumentInvalidException, FileNotFoundException, PersistenceException{
		return gerenteDados.getGerentePosts().informacaoDoPost(idPost, atributo);
		
	}
	

	public void logoff(String idSessao) throws ArgumentInvalidException{
		gerenteDados.getGerenteSessoes().logoff(idSessao);
	}
	
	public void saveData() throws PersistenceException, IOException{
		gerenteDados.saveData();
	}
}
