package classes.gerenciadores;

import interfaces.Constantes;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import classes.Blog;
import classes.gerenciadores.GerenciadorDeSessoes;
import classes.func.usuario.Usuario;
import persistencia.daos.BlogsDAO;
import persistencia.daos.UsuariosDAO;

public class GerenciadorDeBlogs {

	private static final int DESCRICAO = 1499866697;
	private static final int TITULO = -873444423;
	private static final int DONO = 3089292;

	private BlogsDAO blogsDAO = BlogsDAO.getInstance();
	private UsuariosDAO userDAO = UsuariosDAO.getInstance();
	private GerenciadorDeSessoes gerenteDeSessao;

	public GerenciadorDeBlogs(GerenciadorDeSessoes gerenteSessoes) {
		this.gerenteDeSessao = gerenteSessoes;
	}

	/**
	 * 
	 * @param idSession
	 * @param titulo
	 * @param descricao
	 * @return
	 * @throws Exception
	 */
	public String createBlog(String idSessao, String titulo, String descricao)
			throws ArgumentInvalidException, PersistenceException, IOException {
		try {
			String login = gerenteDeSessao.getLogin(idSessao);
			Blog blog = new Blog(titulo, descricao, idSessao);

			Usuario us = userDAO.recupera(login);
			us.listaDeBlogs().add(blog);
			blogsDAO.criar(blog);
			return blog.getId();

		} catch (ArgumentInvalidException e) {
			throw e;

		}
	}
	
	public String getAtributo(Blog b, String atributo) throws ArgumentInvalidException {
		if (atributo == null || atributo.equals(""))
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO2);
		
		int codigoAtributo = atributo.hashCode();
		
		switch(codigoAtributo) {
			
			case(TITULO):
				return b.getTitulo().toString();
		
			case(DESCRICAO):
				return b.getDescricao().toString();
			
			case(DONO):
				String idSessao = b.getIdSessao();
				return gerenteDeSessao.getLogin(idSessao);
			default:
				throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO2);
		}
				
	}

	public void changeBlogInformation(Blog blog, String atributo,
			String novoValor) throws ArgumentInvalidException, PersistenceException, IOException {
		
		if (atributo == null) throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO2);
		
		int codigoAtributo = atributo.hashCode();
		switch(codigoAtributo) {
			
			case(TITULO):
				blog.setTitulo(novoValor);
				break;

			case(DESCRICAO):
				blog.setDescricao(novoValor);
				break;
			
			case(DONO):
			default:
				throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO2);
		}
		blogsDAO.atualizar(blog);
		
	}

	public Blog getBlog(String idBlog) throws FileNotFoundException, PersistenceException{
		return blogsDAO.recupera(idBlog);
	}
}