package classes.gerenciadores;

import interfaces.Constantes;
import interfaces.Gerenciador;
import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.BlogsDAO;
import persistencia.daos.PostsDAO;
import persistencia.daos.UsuariosDAO;
import classes.gerenciadores.GerenciadorDeBlogs;
import classes.Post;
import classes.Blog;
import classes.Texto;

public class GerenciadorDePosts implements Gerenciador {
	private BlogsDAO blogsDAO = BlogsDAO.getInstance();
	private PostsDAO postsDAO = PostsDAO.getInstance();
	private UsuariosDAO userDAO = UsuariosDAO.getInstance();
	private GerenciadorDeBlogs gerenteDeBlogs;
	private GerenciadorDeSessoes gerenteDeSessao;
	
	private static final int TEXTO = 110256354;
	private static final int TITULO = -873444423;
	
	public GerenciadorDePosts(GerenciadorDeSessoes gereteDeSessao, GerenciadorDeBlogs gerenteBlogs){
		this.gerenteDeSessao = gereteDeSessao;
		this.gerenteDeBlogs = gerenteBlogs;
		
	}
	
	/**
	 * Metodo que gerancia a criacao de um Post.
	 * @param idBlog
	 * @param titulo
	 * @param texto
	 * @param texto2 
	 * @return
	 * @throws IOException
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 */
	public String createPost(String idSessao, String idBlog, String titulo, String texto) throws IOException, ArgumentInvalidException, PersistenceException {
		Post post;
		try {
			gerenteDeSessao.getLogin(idSessao);
			Blog blog = gerenteDeBlogs.getBlog(idBlog);
			Texto txt = new Texto(titulo, texto);
			post = new Post(txt, idBlog);
			blog.listaDePosts().add(post);
			postsDAO.criar(post);
			blogsDAO.atualizar(blog);
			
			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (PersistenceException e) {
			throw e;
		} catch (ArgumentInvalidException e) {
			throw e;
		}catch (IOException e) {
			throw e;
		}
			
		return post.getId();		
	}
	
	public String getAtributo(Post post, String atributo) throws ArgumentInvalidException {
		int codigoAtributo = atributo.hashCode();
		
		switch(codigoAtributo) {
			
			case(TEXTO):
				return post.getTexto().getCorpo().toString();
			case(TITULO):
				return post.getTexto().getTitulo().toString();
			default:
				throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO2);
		}
	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadData() {
		
		
	}
	
	public Blog getBlog(String idBlog, String idSessao) throws FileNotFoundException, PersistenceException, ArgumentInvalidException{
		Blog blog = blogsDAO.recupera(idBlog);
		if(!idSessao.equals(blog.getIdSessao())){
			throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);
		}
		return blog;
	}

	public int getNumberOfComments(String postId) throws FileNotFoundException, PersistenceException {
		return postsDAO.recupera(postId).getNumberOfComments();
	}
}
