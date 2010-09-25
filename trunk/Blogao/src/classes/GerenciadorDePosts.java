package classes;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.BlogsDAO;
import persistencia.daos.PostsDAO;
import persistencia.daos.UsuariosDAO;
import classes.func.GerenciadorDeBlogs;
import classes.func.usuario.Usuario;

public class GerenciadorDePosts {
	private BlogsDAO blogsDAO = BlogsDAO.getInstance();
	private PostsDAO postsDAO = PostsDAO.getInstance();
	private UsuariosDAO userDAO = UsuariosDAO.getInstance();
	private GerenciadorDeBlogs gerenteDeBlogs;
	private GerenciadorDeSessoes gerenteDeSessao;
	
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
}
