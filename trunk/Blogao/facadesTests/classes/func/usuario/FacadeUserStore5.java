package classes.func.usuario;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.BlogsDAO;
import persistencia.daos.EmailsDAO;
import persistencia.daos.PostsDAO;
import persistencia.daos.UsuariosDAO;
import interfaces.Logavel;
import classes.Blog;
import classes.Email;
import classes.GerenciadorDeSessoes;
import classes.Login;
import classes.Senha;
import classes.Texto;

/**
 * Facade de alteracoes do blog. Classe necessaria para testes.
 * @author Tiago
 */
public class FacadeUserStore5 {
	private Perfil perfil1;
	private Usuario user1;
	private GerenciadorDeSessoes gerente = new GerenciadorDeSessoes();
	private Blog blog;

	private UsuariosDAO userDAO = UsuariosDAO.getInstance();
	private BlogsDAO blogsDAO = BlogsDAO.getInstance();
	private EmailsDAO emailsDAO = EmailsDAO.getInstance();
	private PostsDAO postsDAO = PostsDAO.getInstance();

	
	//TODO APAGAR OS DADOS SALVOS
	public void cleanPersistence() {
		userDAO.limparUsuarios();
		blogsDAO.limparBlogs();
		emailsDAO.limparEmails();
		postsDAO.limparPosts();
	}
	
	//TODO Armazenar no BD.
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception {

		Login log = new Login(login);
		Senha sen = new Senha(senha);
		Email mail = new Email(email);

		perfil1 = new Perfil();
		perfil1.setNomeDeExibicao(nome_exibicao);
		perfil1.setEmail(mail);
		perfil1.setSexo(sexo);
		perfil1.setDataDeNascimento(dataNasc);
		perfil1.setEndereco(endereco);
		perfil1.setInteresses(interesses);
		perfil1.setQuemSouEu(quem_sou_eu);
		perfil1.setFilmesFavoritos(filmes);
		perfil1.setMusicasFavoritas(musicas);
		perfil1.setLivrosFavoritos(livros);

		user1 = new Usuario(log, sen, perfil1);

		emailsDAO.criar(mail);
		userDAO.criar(user1);
	}
	
	// METODO QUE LOGA O USUARIO
	public String logon(String login, String senha) throws FileNotFoundException,
			ArgumentInvalidException, PersistenceException {
		try {
			return gerente.logon(login, senha);
		} catch (PersistenceException e) {
			throw e;
		}
	}
	
	//TODO CRIA O BLOG
	public void createBlog(String idSession, String titulo, String descricao){	
		try {
			Texto desc = new Texto(descricao);
			//blog = new Blog(titulo, desc);

			blogsDAO.criar(blog);
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ArgumentInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//TODO RETORNA OS ATRIBUTOS DO BLOG.
	public void getBlogInformation(String titulo, String descricao){
		blog.getTitulo();
		blog.getDescricao();
	}
	
	//TODO REALIZA AS MUDANCAS NO BLOG.
	public void changeBlogInformation(String idSession, String id, String titulo, String valor) throws FileNotFoundException, PersistenceException{


	}
	
	//TODO METODO QUE DESLOGA O USUARIO.
	public void logoff(String idSession) throws ArgumentInvalidException{
		try {
			gerente.logoff(idSession);
		} catch (ArgumentInvalidException e){
			throw e;
		}
	}
	
	//TODO SALVA TODOS OS DADOS NO BD
	public void saveData() {}
}
