package facadesUserStores;

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
import classes.Email;
import classes.GerenciadorDeSessoes;
import classes.Login;
import classes.Senha;
import classes.func.usuario.Perfil;
import classes.func.usuario.Usuario;

public class FacadeUserStore8 {
	private Perfil perfil1;
	private Usuario user1;
	private GerenciadorDeSessoes gerente = new GerenciadorDeSessoes();
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
	
	//TODO RETORNA O ID DO BLOG DADO O LOGIN DO USUARIO E O INDICE.
	public String getBlogByLogin(String login, int index){
		return null;
		
	}
	
	//TODO RETORNA O ID DO POST DADO O ID DO BLOG E O INDICE.
	public String getPost(String blogId, int index){
		return null;
	}
	
	//TODO ADICIONA UM COMENTARIO. o RETORNO DO METODO E O ID DO COMENTARIO
	public String addComment(String sessionId, String postId, String texto){
		return null;
	}
	
	//TODO RETORNA O NUMERO DE COMETRAIOS DO POST.
	public int getNumberOfComments(String postId){
		return 0;
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
}
