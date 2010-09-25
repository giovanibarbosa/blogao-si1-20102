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
	
	public GerenciadorDePosts(GerenciadorDeBlogs gerenteBlogs){
		this.gerenteDeBlogs = gerenteBlogs;
	}
	
	/**
	 * Metodo que gerancia a criacao de um Post.
	 * @param idBlog
	 * @param titulo
	 * @param texto
	 * @return
	 * @throws IOException
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 */
	public String createPost(String idBlog, String titulo, String texto) throws IOException, ArgumentInvalidException, PersistenceException {
		Post post;
		try {
			Blog blog = gerenteDeBlogs.getBlog(idBlog);
			Texto txt = new Texto(titulo, texto);
			post = new Post(txt, idBlog);
			blog.listaDePosts().add(post);
			postsDAO.criar(post);
			
			
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
