package classes.func.usuario;

import persistencia.daos.BlogsDAO;
import persistencia.daos.ComentariosDAO;
import persistencia.daos.EmailsDAO;
import persistencia.daos.PostsDAO;
import persistencia.daos.UsuariosDAO;

public class FacadeUserStorie10 {

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
	
}
