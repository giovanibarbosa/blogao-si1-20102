package classes.func;

import ourExceptions.ArgumentInvalidException;
import classes.Blog;
import classes.GerenciadorDeSessoes;
import classes.func.usuario.Usuario;
import persistencia.daos.BlogsDAO;
import persistencia.daos.UsuariosDAO;

public class GerenciadorDeBlogs {
	
	private BlogsDAO blogsDAO = BlogsDAO.getInstance();
	private UsuariosDAO userDAO = UsuariosDAO.getInstance();
	
	private GerenciadorDeSessoes gerente = new GerenciadorDeSessoes();
	
	
	/**
	 * 
	 * @param idSession
	 * @param titulo
	 * @param descricao
	 * @return
	 * @throws Exception
	 */
	public String createBlog(String idSession, String titulo, String descricao) throws Exception{
		try {
			Blog blog = new Blog(titulo, descricao);
			String login = gerente.getLogin(idSession);
			Usuario us = userDAO.recupera(login);
			us.listaDeBlogs().add(blog);
			blogsDAO.criar(blog);
			return String.valueOf(blog.getId());
		
		} catch (ArgumentInvalidException e) {
			throw e;
			
		} catch (Exception e) {
			throw e;
		}
	}

}
