package classes.gerenciadores;

import interfaces.Constantes;
import interfaces.Gerenciador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import classes.Blog;
import classes.func.usuario.Usuario;
import persistencia.daos.BlogsDAO;

public class GerenciadorDeBlogs implements Gerenciador {

	private BlogsDAO blogsDAO = BlogsDAO.getInstance();

	private GerenciadorDeDados gerenteDados;

	private List<Blog> listaDeBlogs;

	private static final int DESCRICAO = 1499866697;
	private static final int TITULO = -873444423;
	private static final int DONO = 3089292;

	public GerenciadorDeBlogs(GerenciadorDeDados gerenteDados) {
		listaDeBlogs = new ArrayList<Blog>();
		this.gerenteDados = gerenteDados;
	}

	public String createBlog(String idSessao, String titulo, String descricao)
			throws ArgumentInvalidException, PersistenceException, IOException,
			UserInvalidException {

		String login = gerenteDados.getGerenteSessoes().getLoginPorSessao(
				idSessao);
		Blog blog = new Blog(titulo, descricao, idSessao);

		Usuario us = gerenteDados.getGerenciadorDeUsuarios().getUsuario(login);

		us.addBlog2(blog);
		gerenteDados.getGerenciadorDeUsuarios().remover(us);
		listaDeBlogs.add(blog);
		gerenteDados.getGerenciadorDeUsuarios().adicionar(us);

		return blog.getId();

	}

	public Blog getBlog(String idBlog) throws ArgumentInvalidException {
		for (Blog blog : listaDeBlogs) {
			if (blog.getId().equals(idBlog))
				return blog;
		}
		throw new ArgumentInvalidException(Constantes.BLOG_INVALIDO);
	}

	public Blog getBlog(String idBlog, String idSessao)
			throws FileNotFoundException, PersistenceException,
			ArgumentInvalidException {
		Blog blog = blogsDAO.recupera(idBlog);
		if (!idSessao.equals(blog.getIdSessao())) {
			throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);
		}
		return blog;
	}

	public Blog getBlogPorLogin(String login, int index)
			throws UserInvalidException {
		Usuario user = gerenteDados.getGerenteUsuarios().getUsuario(login);
		return user.getListaBlogs().get(index);
	}

	public List<Blog> getBlogPorLogin(String login) throws UserInvalidException {
		Usuario user = gerenteDados.getGerenteUsuarios().getUsuario(login);
		return user.getListaBlogs();
	}

	public int totalDeBlogsPorLogin(String login)
			throws ArgumentInvalidException, FileNotFoundException,
			PersistenceException, UserInvalidException {
		Usuario user = gerenteDados.getGerenteUsuarios().getUsuario(login);
		return user.getListaBlogs().size();
	}

	public int totalDeBlogsPorSessao(String sessionID)
			throws ArgumentInvalidException, FileNotFoundException,
			PersistenceException, UserInvalidException {
		int cont = 0;
		for (Blog blog : listaDeBlogs) {
			if (blog.getIdSessao().equals(sessionID)) {
				cont++;
			}
		}
		return cont;
	}

	public String recuperaIdBlogDesejado(String sessionID, int index)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException, UserInvalidException {

		int indexAtual = 0;
		for (Blog blog : listaDeBlogs) {
			if (blog.getIdSessao().equals(sessionID)) {
				if (indexAtual == index) {
					return blog.getId();
				} else {
					indexAtual++;
				}

			}
		}
		throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO);

		// String login = gerenteDados.getGerenteSessoes().getLoginPorSessao(
		// sessionID);
		// Usuario user = gerenteDados.getGerenteUsuarios().getUsuario(login);
		// return user.getListaBlogs().get(index).getId();

	}

	public String recuperaIdBlogPorLogin(String login, int index)
			throws FileNotFoundException, PersistenceException,
			UserInvalidException, ArgumentInvalidException {
		String idSession = String.valueOf(login.hashCode());
		return recuperaIdBlogDesejado(idSession, index);

		// Usuario user = gerenteDados.getGerenteUsuarios().getUsuario(login);
		//		
		// return user.getListaBlogs().get(index).getId();
	}

	public int totalDePosts(String idBlog) throws FileNotFoundException,
			PersistenceException, ArgumentInvalidException {
		return getBlog(idBlog).getListaDePostagens().size();

	}

	public String recuperaIdDoPost(String idBlog, int index)
			throws NumberFormatException, FileNotFoundException,
			PersistenceException, ArgumentInvalidException {
		return getBlog(idBlog).getListaDePostagens().get(index);
	}

	public String getAtributo(Blog b, String atributo)
			throws ArgumentInvalidException {
		if (atributo == null || atributo.equals(""))
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO2);

		int codigoAtributo = atributo.hashCode();

		switch (codigoAtributo) {

		case (TITULO):
			return b.getTitulo().toString();

		case (DESCRICAO):
			return b.getDescricao().toString();

		case (DONO):
			String idSessao = b.getIdSessao();
			return gerenteDados.getGerenteSessoes().getLoginPorSessao(idSessao);
		default:
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO2);
		}

	}

	public Blog changeBlogInformation(Blog blog, String atributo,
			String novoValor) throws ArgumentInvalidException,
			PersistenceException, IOException {

		if (atributo == null)
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO2);

		int codigoAtributo = atributo.hashCode();
		switch (codigoAtributo) {

		case (TITULO):
			blog.setTitulo(novoValor);
			return blog;

		case (DESCRICAO):
			blog.setDescricao(novoValor);
			return blog;

		case (DONO):
		default:
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO2);
		}

	}

	public void changeBlogInformation(String idSessao, String idBlog,
			String atributo, String novoValor) throws ArgumentInvalidException,
			PersistenceException, UserInvalidException, IOException {

		String login = gerenteDados.getGerenteSessoes().getLoginPorSessao(
				idSessao);

		if (!gerenteDados.getGerenteSessoes().isUserLogged(login)) {
			throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);
		}

		Usuario us = gerenteDados.getGerenciadorDeUsuarios().getUsuario(login);
		if (verificaExistenciaDeBlog(idBlog)) {
			Blog blog = getBlog(idBlog);
			validaDonoBlog(blog, idSessao);
			us.removeBlog2(blog);
			us.addBlog2(changeBlogInformation(blog, atributo, novoValor));
		}

	}

	public void validaDonoBlog(Blog blog, String idSessao)
			throws ArgumentInvalidException {
		if (!blog.getIdSessao().equals(idSessao)) {
			throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);
		}

	}

	public String getBlogInformation(String idBlog, String atributo)
			throws ArgumentInvalidException, FileNotFoundException,
			PersistenceException {

		Blog blog = gerenteDados.getGerenteBlogs().getBlog(idBlog);
		return gerenteDados.getGerenteBlogs().getAtributo(blog, atributo);

	}

	/**
	 * @return the listaDeBlogs
	 */
	public List<Blog> getListaDeBlogs() {
		return listaDeBlogs;
	}

	/**
	 * @param listaDeBlogs
	 *            the listaDeBlogs to set
	 */
	public void setListaDeBlogs(List<Blog> listaDeBlogs) {
		this.listaDeBlogs = listaDeBlogs;
	}

	public boolean verificaExistenciaDeBlog(String idBlog)
			throws ArgumentInvalidException {
		for (Blog blog : listaDeBlogs) {
			if (blog.getId().equals(idBlog)) {
				return true;
			}
		}
		throw new ArgumentInvalidException(Constantes.BLOG_INVALIDO);
	}

	@Override
	public void saveData() throws PersistenceException, IOException {
		blogsDAO.limparBlogs();
		for (Blog blog : listaDeBlogs) {
			blogsDAO.criar(blog);
		}

	}

	public void loadData() {
		try {
			listaDeBlogs = blogsDAO.recuperaBlogs();
		} catch (FileNotFoundException e) {
			listaDeBlogs = new ArrayList<Blog>();
		}

	}

	@Override
	public void cleanPersistence() {
		blogsDAO.limparBlogs();

	}

	public List<String> getBlogPorNome(String match) {
		List<String> listaBlog = new ArrayList<String>();
		List<Blog> listAllBlogs = gerenteDados.getGerenteBlogs()
				.getListaDeBlogs();
		for (Blog blg : listAllBlogs) {
			if (blg.getTitulo().equals(match))
				listaBlog.add(blg.getId());
		}
		return listaBlog;
	}

	public void deleteBlog(String sessionId, String blogId) throws FileNotFoundException, PersistenceException, ArgumentInvalidException {
		Blog blog = getBlog(blogId, sessionId);
		listaDeBlogs.remove(blog);
	}

}
