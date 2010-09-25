package classes.func;

import interfaces.Constantes;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import classes.Blog;
import classes.GerenciadorDeSessoes;
import classes.func.usuario.Usuario;
import persistencia.daos.BlogsDAO;
import persistencia.daos.UsuariosDAO;

public class GerenciadorDeBlogs {
	
	private BlogsDAO blogsDAO = BlogsDAO.getInstance();
	private UsuariosDAO userDAO = UsuariosDAO.getInstance();
	
	private GerenciadorDeSessoes gerenteDeSessao = new GerenciadorDeSessoes();
	
	
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
			String login = gerenteDeSessao.getLogin(idSession);
			
			Usuario us = userDAO.recupera(login);
			gerenteDeSessao.logon(login, us.getSenha().getSenha());
			us.listaDeBlogs().add(blog);
			blogsDAO.criar(blog);
			return blog.getId();
		
		} catch (ArgumentInvalidException e) {
			throw e;
			
		} catch (Exception e) {
			throw e;
		}
	}
	
		

}
