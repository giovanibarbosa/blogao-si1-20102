package classes.func.usuario;

import java.io.FileNotFoundException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import classes.Comentario;
import classes.gerenciadores.GerenciadorDeDados;
import classes.gerenciadores.GerenciadorDeSessoes;

public class FacadeUserStore9 {
	private GerenciadorDeSessoes gerenteSessoes = new GerenciadorDeSessoes();
	private GerenciadorDeDados gerenteDados = new GerenciadorDeDados();

	
	// CARREGA TODOS OS DADOS DO BD
	public void loadData() throws FileNotFoundException {
		gerenteDados.loadData();
	}
	
	// METODO QUE LOGA O USUARIO
	public String logon(String login, String senha) throws FileNotFoundException,
			ArgumentInvalidException, PersistenceException {
		return gerenteSessoes.logon(login, senha);
	}
	
	//TODO RETORNA O ID DO BLOG DADO O LOGIN DO USUARIO E O INDICE.
	public String getBlogByLogin(String login, int index){
		return null;
		
	}
	
	//TODO RETORNA O ID DO POST DADO O ID DO BLOG E O INDICE.
	public String getPost(String blogId, int index){
		return null;
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
		
	}
	
	//TODO RETORNA AS INFORMACOES DO POST.
	public void getPostInformation(String idPost,String atributo){
		
	}
	
	//TODO METODO QUE DESLOGA O USUARIO.
	public void logoff(String idSession) throws ArgumentInvalidException{
		gerenteSessoes.logoff(idSession);
	}
	
	//TODO SALVA TODOS OS DADOS NO BD
	public void saveData() {}
}
