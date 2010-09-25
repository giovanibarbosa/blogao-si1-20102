package facadesUserStores;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.BlogsDAO;
import persistencia.daos.EmailsDAO;
import persistencia.daos.PostsDAO;
import persistencia.daos.UsuariosDAO;
import interfaces.Logavel;
import classes.Blog;
import classes.Email;
import classes.GerenciadorDePerfis;
import classes.GerenciadorDeSessoes;
import classes.Login;
import classes.Senha;
import classes.Texto;
import classes.func.usuario.Perfil;
import classes.func.usuario.Usuario;

/**
 * Facade de alteracoes do blog. Classe necessaria para testes.
 * @author Tiago
 */
public class FacadeUserStore5 {
	private Perfil perfil1;
	private Usuario user1;
	private GerenciadorDeSessoes gerente = new GerenciadorDeSessoes();
	private GerenciadorDePerfis gerentePerfis = new GerenciadorDePerfis();
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
	
	//Armazenar no BD.
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception {
		
		try {
			gerentePerfis.createProfile(login, senha, nome_exibicao, email, sexo,
					dataNasc, endereco, interesses, quem_sou_eu, filmes, musicas, livros);
			
		} catch (Exception e) {
			throw e;
		}
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
	public void createBlog(String idSession, String titulo, String descricao) throws ArgumentInvalidException, PersistenceException, IOException{	
			blog = new Blog(titulo, descricao);
			blogsDAO.criar(blog);

	}
	
	//TODO RETORNA OS ATRIBUTOS DO BLOG.
	public void getBlogInformation(String titulo, String descricao){
		blog.getTitulo();
		blog.getDescricao();
	}
	
	//TODO REALIZA AS MUDANCAS NO BLOG.
	public void changeBlogInformation(String idSessao, String atributo, String id,
		String novoValor) throws Exception {
		
		try {
			String login = gerente.getLogin(idSessao);
			Usuario us = userDAO.recupera(login);
			List<Blog> listBlog = us.getListaBlogs();
			
			Blog blog = null;
			Blog blogMod = null;

			/*for(int i = listBlog.size(); i > 1; i--){
				if(listBlog.get(i).getId().equals(id)){
					blog = listBlog.get(i);
					blogMod = listBlog.get(i);
				}
			}
			
			if(blog == null){
				throw new Exception("Blog inválido");
			}*/
			
			if(novoValor != null && !(novoValor.trim().equals(""))){
				if(atributo.equals("descricao")){
					blogMod.setDescricao(novoValor);
				}else if(atributo.equals("titulo")){
					blogMod.setTitulo(novoValor);
				}else{
					throw new Exception("Atributo inválido");
				}
				
				blogsDAO.atualizar(blog, blogMod);
				
			}else{
				throw new Exception("Você deve especificar um título para o blog");
			}
			
			
			
			
		} catch (ArgumentInvalidException e) {
			throw e;
		} catch (FileNotFoundException e) {
			throw e;
		} catch (PersistenceException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}

		

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

