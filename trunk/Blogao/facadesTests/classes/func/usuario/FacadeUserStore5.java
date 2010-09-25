package classes.func.usuario;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.BlogsDAO;
import persistencia.daos.EmailsDAO;
import persistencia.daos.PostsDAO;
import persistencia.daos.UsuariosDAO;
import interfaces.Constantes;
import interfaces.Logavel;
import classes.Blog;
import classes.Email;
import classes.GerenciadorDePerfis;
import classes.GerenciadorDeSessoes;
import classes.Login;
import classes.Senha;
import classes.Texto;
import classes.func.GerenciadorDeBlogs;

/**
 * Facade de alteracoes do blog. Classe necessaria para testes.
 * 
 * @author Tiago
 * @author Rodolfo
 */
public class FacadeUserStore5 {
	private Perfil perfil1;
	private Usuario user1;
	private GerenciadorDeSessoes gerente = new GerenciadorDeSessoes();
	private GerenciadorDePerfis gerentePerfis = new GerenciadorDePerfis();
	private GerenciadorDeBlogs gerenteBlogs = new GerenciadorDeBlogs(gerente);
	private Blog blog;

	private UsuariosDAO userDAO = UsuariosDAO.getInstance();
	private BlogsDAO blogsDAO = BlogsDAO.getInstance();
	private EmailsDAO emailsDAO = EmailsDAO.getInstance();
	private PostsDAO postsDAO = PostsDAO.getInstance();

	// TODO APAGAR OS DADOS SALVOS
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

		try {
			gerentePerfis.createProfile(login, senha, nome_exibicao, email,
					sexo, dataNasc, endereco, interesses, quem_sou_eu, filmes,
					musicas, livros);

		} catch (Exception e) {
			throw e;
		}
	}

	// METODO QUE LOGA O USUARIO
	public String logon(String login, String senha)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException {
		try {
			return gerente.logon(login, senha);
		} catch (PersistenceException e) {
			throw e;
		}
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

	// TODO REALIZA AS MUDANCAS NO BLOG.
	public void changeBlogInformation(String idSessao, String id,
			String atributo, String novoValor) throws PersistenceException, ArgumentInvalidException, IOException {
		try{
			if (!gerente.getLogados().containsKey(idSessao)) throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);
			
			blog = blogsDAO.recupera(id);
			System.out.println(blog.getIdSessao());
			System.out.println(idSessao + "\n");
			gerenteBlogs.changeBlogInformation(blog, atributo, novoValor);
			if (!blog.getIdSessao().equals(idSessao)) throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);
			
		} catch (ArgumentInvalidException e){
			throw e;
		}
	}

	// METODO QUE DESLOGA O USUARIO.
	public void logoff(String idSession) throws ArgumentInvalidException {
		try {
			gerente.logoff(idSession);
		} catch (ArgumentInvalidException e) {
			throw e;
		}
	}

	// TODO SALVA TODOS OS DADOS NO BD
	public void saveData() {
	}

}
