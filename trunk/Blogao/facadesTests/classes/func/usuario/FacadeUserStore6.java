package classes.func.usuario;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;

import persistencia.daos.BlogsDAO;
import persistencia.daos.EmailsDAO;
import persistencia.daos.PostsDAO;
import persistencia.daos.UsuariosDAO;
import classes.Email;
import classes.gerenciadores.GerenciadorDePosts;
import classes.gerenciadores.GerenciadorDeSessoes;
import classes.Login;
import classes.Senha;
import classes.gerenciadores.GerenciadorDeBlogs;

public class FacadeUserStore6 {
	private Perfil perfil1;
	private Usuario user1;
	private GerenciadorDeSessoes gerente = new GerenciadorDeSessoes();
	private GerenciadorDeBlogs gerenteBlog = new GerenciadorDeBlogs(gerente);
	private GerenciadorDePosts gerentePost = new GerenciadorDePosts(gerente, gerenteBlog);
	
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
	
	//TODO RETORNA A DATA DE ATUAL.
	public String todaysDate(){
		Date data = new Date();  
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 
		return formatador.format(data);
		
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
	
	// TODO METODO QUE LOGA O USUARIO
	public String logon(String login, String senha) throws PersistenceException, FileNotFoundException, ArgumentInvalidException{
		try {
			return gerente.logon(login, senha);
		} catch (PersistenceException e){
			throw e;
		}

	}
	
	//TODO CRIA O BLOG
	public void createBlog(String idSession, String titulo, String descricao) throws Exception{	
		
		try {
			gerenteBlog.createBlog(idSession, titulo, descricao);
		} catch (Exception e) {
			throw e;
		}

	}
	
	//TODO CRIA O POST
	public String createPost(String idSession, String idBlog, String titulo, String texto) throws ArgumentInvalidException, PersistenceException, IOException{
		return gerentePost.createPost(idSession, idBlog, titulo, texto);

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
