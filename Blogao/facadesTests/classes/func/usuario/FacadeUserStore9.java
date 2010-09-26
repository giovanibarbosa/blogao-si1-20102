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
import classes.gerenciadores.GerenciadorDeDados;
import classes.gerenciadores.GerenciadorDeSessoes;

public class FacadeUserStore9 {
	private Perfil perfil1;
	private Usuario user1;
	private GerenciadorDeSessoes gerenteSessoes = new GerenciadorDeSessoes();
	private GerenciadorDeDados gerenteDados = new GerenciadorDeDados();
	private Blog blog;

	private UsuariosDAO userDAO;
	private BlogsDAO blogsDAO;
	private EmailsDAO emailsDAO;
	private PostsDAO postsDAO;
	private ComentariosDAO comentsDAO;
	
	// CARREGA TODOS OS DADOS DO BD
	public void loadData() {
		userDAO = UsuariosDAO.getInstance();
		blogsDAO = BlogsDAO.getInstance();
		emailsDAO = EmailsDAO.getInstance();
		postsDAO = PostsDAO.getInstance();
		comentsDAO = ComentariosDAO.getInstance();
	}
	
	// METODO QUE LOGA O USUARIO
	public String logon(String login, String senha) throws FileNotFoundException,
			ArgumentInvalidException, PersistenceException {
		try {
			return gerenteSessoes.logon(login, senha);
		} catch (PersistenceException e) {
			throw e;
		}
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
		try {
			gerenteSessoes.logoff(idSession);
		} catch (ArgumentInvalidException e){
			throw e;
		}
	}
	
	//TODO SALVA TODOS OS DADOS NO BD
	public void saveData() {}
}
