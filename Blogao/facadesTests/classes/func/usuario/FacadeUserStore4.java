package classes.func.usuario;

import java.io.FileNotFoundException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.BlogsDAO;
import persistencia.daos.EmailsDAO;
import persistencia.daos.PostsDAO;
import persistencia.daos.UsuariosDAO;
import classes.Blog;
import classes.gerenciadores.GerenciadorDePerfis;
import classes.gerenciadores.GerenciadorDeSessoes;
import classes.gerenciadores.GerenciadorDeBlogs;

/**
 * Facade de Blog. Essa classe chama os metodos necess�rios para o teste.
 * @author Tiago
 * @author Rodolfo Marinho
 */
public class FacadeUserStore4 {
	private GerenciadorDeSessoes gerente = new GerenciadorDeSessoes();
	private GerenciadorDeBlogs gerenciaBlogs = new GerenciadorDeBlogs();
	private GerenciadorDePerfis gerentePerfis = new GerenciadorDePerfis();
	
	private UsuariosDAO userDAO = UsuariosDAO.getInstance();
	private BlogsDAO blogsDAO = BlogsDAO.getInstance();
	private EmailsDAO emailsDAO = EmailsDAO.getInstance();
	private PostsDAO postsDAO = PostsDAO.getInstance();
	
	//APAGAR OS DADOS SALVOS
	public void cleanPersistence() {
		userDAO.limparUsuarios();
		blogsDAO.limparBlogs();
		emailsDAO.limparEmails();
		postsDAO.limparPosts();		
	}
	
	//Armazenar no BD.
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception {
		
		gerentePerfis.createProfile(login, senha, nome_exibicao, email, sexo,
					dataNasc, endereco, interesses, quem_sou_eu, filmes, musicas, livros);

	}
	
	public String createBlog(String idSession, String titulo, String descricao) throws Exception {
		return gerenciaBlogs.createBlog(idSession, titulo, descricao);		
			

	}
	
	//RETORNA OS ATRIBUTOS DO BLOG.
	public String getBlogInformation(String idBlog, String atributo) throws ArgumentInvalidException, FileNotFoundException {
		Blog blog = blogsDAO.recuperaBlogPorId(idBlog);
		return gerenciaBlogs.getAtributo(blog, atributo);	
	}
	

	public String logon(String login, String senha)throws PersistenceException, FileNotFoundException, ArgumentInvalidException {
		return gerente.logon(login, senha);

	}
	
	public void saveData() {}
	
	public void logoff(String idSession) throws Exception {
		gerente.logoff(idSession);			

	}
	
	
}
