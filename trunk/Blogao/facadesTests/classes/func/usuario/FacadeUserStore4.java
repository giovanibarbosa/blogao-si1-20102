package classes.func.usuario;

import java.io.FileNotFoundException;

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
import classes.func.GerenciadorDeBlogs;

/**
 * Facade de Blog. Essa classe chama os metodos necess�rios para o teste.
 * @author Tiago
 */
public class FacadeUserStore4 {
	private Blog blog;
	private Perfil perfil1;
	private Usuario user1;
	private GerenciadorDeSessoes gerente = new GerenciadorDeSessoes();
	private GerenciadorDeBlogs gerenciaBlogs = new GerenciadorDeBlogs();
	
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
	
	//Armazenar no BD.
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
	
	//TODO CRIA O BLOG
	public String createBlog(String idSession, String titulo, String descricao) throws Exception{
		try {
			return gerenciaBlogs.createBlog(idSession, titulo, descricao);		
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	//TODO RETORNA OS ATRIBUTOS DO BLOG.
	public void getBlogInformation(String idBLog, String atributo) {
		
	}
	
	/**
	 * 
	 * @param login
	 * @param senha
	 * @return
	 * @throws PersistenceException
	 * @throws FileNotFoundException
	 * @throws ArgumentInvalidException
	 */
	public String logon(String login, String senha)throws PersistenceException, FileNotFoundException, ArgumentInvalidException {
		try {
			return gerente.logon(login, senha);
		} catch (PersistenceException e){
			throw e;
		}
	}
	
	public void saveData() {}
	
	public void logoff(String idSession) throws Exception {
		try {
			gerente.logoff(idSession);			
		} catch (Exception e) {
			throw e;
		}
	}
	
	
}
