package classes.func.usuario;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.BlogsDAO;
import persistencia.daos.EmailsDAO;
import persistencia.daos.PostsDAO;
import persistencia.daos.UsuariosDAO;
import interfaces.Constantes;
import classes.Blog;
import classes.gerenciadores.GerenciadorDePerfis;
import classes.gerenciadores.GerenciadorDeSessoes;
import classes.gerenciadores.GerenciadorDeBlogs;

/**
 * Facade de alteracoes do blog. Classe necessaria para testes.
 * 
 * @author Tiago
 * @author Rodolfo
 */
public class FacadeUserStore5 {
	private GerenciadorDeSessoes gerente = new GerenciadorDeSessoes();
	private GerenciadorDePerfis gerentePerfis = new GerenciadorDePerfis();
	private GerenciadorDeBlogs gerenteBlogs = new GerenciadorDeBlogs(gerente);
	private Blog blog;

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

	// Armazenar no BD.
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception {

		gerentePerfis.createProfile(login, senha, nome_exibicao, email,
					sexo, dataNasc, endereco, interesses, quem_sou_eu, filmes,
					musicas, livros);

	}

	// METODO QUE LOGA O USUARIO
	public String logon(String login, String senha)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException {
		return gerente.logon(login, senha);

	}

	// CRIA O BLOG
	public String createBlog(String idSessao, String titulo, String descricao)
			throws ArgumentInvalidException, PersistenceException, IOException {
		blog = new Blog(titulo, descricao, idSessao);
		blogsDAO.criar(blog);
		return blog.getId();
	}

	// RETORNA OS ATRIBUTOS DO BLOG.
	public String getBlogInformation(String id, String atributo)
			throws FileNotFoundException, PersistenceException,
			ArgumentInvalidException {
		blog = blogsDAO.recupera(id);
		return gerenteBlogs.getAtributo(blog, atributo);
	}

	//REALIZA AS MUDANCAS NO BLOG.
	public void changeBlogInformation(String idSessao, String id,
			String atributo, String novoValor) throws PersistenceException,
			ArgumentInvalidException, IOException {
		if (!gerente.getLogados().containsKey(idSessao))
			throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);

		blog = blogsDAO.recupera(id);
		gerenteBlogs.changeBlogInformation(blog, atributo, novoValor);
		if (!blog.getIdSessao().equals(idSessao))
			throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);

	}

	// METODO QUE DESLOGA O USUARIO.
	public void logoff(String idSession) throws ArgumentInvalidException {
		gerente.logoff(idSession);

	}

	// TODO SALVA TODOS OS DADOS NO BD
	public void saveData() {
	}

}
