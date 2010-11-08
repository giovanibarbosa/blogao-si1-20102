package facades;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;

import classes.Blog;
import classes.gerenciadores.GerenciadorDeBlogs;

public class FacadeBlog {

	private static FacadeBlog instance;
	private GerenciadorDeBlogs gerenteBlog;

	protected FacadeBlog() {
		gerenteBlog = GerenciadorDeBlogs.getInstance();
	}

	public static FacadeBlog getInstance() {
		if (instance == null) {
			instance = new FacadeBlog();
		}
		return instance;
	}

	public String createBlog(String idSessao, String titulo, String descricao)
			throws Exception {
		return gerenteBlog.createBlog(idSessao, titulo, descricao);
	}

	public Blog getBlogByIdBlog(String idBlog) throws ArgumentInvalidException {
		return gerenteBlog.getBlog(idBlog);
	}

	public Blog getBlogByIdBlogAndIdSession(String idBlog, String idSessao)
			throws Exception {
		return gerenteBlog.getBlog(idBlog, idSessao);
	}

	public List<Blog> getBlogsByLogin(String login) throws Exception {
		return gerenteBlog.getBlogPorLogin(login);
	}

	public int totalBlogsByLogin(String login) throws FileNotFoundException,
			ArgumentInvalidException, PersistenceException,
			UserInvalidException {
		return gerenteBlog.totalDeBlogsPorLogin(login);
	}

	public int totalBlogsBySessionId(String sessionId)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException, UserInvalidException {
		return gerenteBlog.totalDeBlogsPorSessao(sessionId);
	}

	public String getIdBlogBySessionIdAndIndex(String sessionId, int index)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException, UserInvalidException {
		return gerenteBlog.recuperaIdBlogDesejado(sessionId, index);
	}

	public String getIdBlogByLoginAndIndex(String login, int index)
			throws FileNotFoundException, PersistenceException,
			UserInvalidException, ArgumentInvalidException {
		return gerenteBlog.recuperaIdBlogPorLogin(login, index);
	}

	public int totalPostsByIdBlog(String blogId) throws FileNotFoundException,
			PersistenceException, ArgumentInvalidException {
		return gerenteBlog.totalDePosts(blogId);
	}

	public String getIdPost(String blogId, int index)
			throws NumberFormatException, FileNotFoundException,
			PersistenceException, ArgumentInvalidException {
		return gerenteBlog.recuperaIdDoPost(blogId, index);
	}

	public String getAtribute(Blog blog, String atribute)
			throws ArgumentInvalidException {
		return gerenteBlog.getAtributo(blog, atribute);
	}

	public Blog changeBlogInformation(Blog blog, String atributo,
			String novoValor) throws ArgumentInvalidException,
			PersistenceException, IOException {
		return gerenteBlog.changeBlogInformation(blog, atributo, novoValor);
	}

	public void changeBlogInformation(String idSessao, String idBlog,
			String atributo, String novoValor) throws Exception {
		gerenteBlog.changeBlogInformation(idSessao, idBlog, atributo, novoValor);
	}

	public void validateBlogOwner(Blog blog, String sessionId)
			throws ArgumentInvalidException {
		gerenteBlog.validaDonoBlog(blog, sessionId);
	}
	
	public String recuperaIdBlogPorLogin(String login, int index)
		throws FileNotFoundException, PersistenceException,
			UserInvalidException, ArgumentInvalidException {
		return gerenteBlog.recuperaIdBlogPorLogin(login, index);
	}
	
	public String recuperaIdBlogDesejado(String sessionID, int index)
		throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException, UserInvalidException {
		return gerenteBlog.recuperaIdBlogDesejado(sessionID, index);
	}

	public String getBlogInformation(String idBlog, String atributo)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException {
		return gerenteBlog.getBlogInformation(idBlog, atributo);
	}

	public List<Blog> getListBlogs() {
		return gerenteBlog.getListaDeBlogs();
	}
	
	public List<String> getBlogPorNome(String match) {
		return gerenteBlog.getBlogPorNome(match);
	}

	public void saveData() throws PersistenceException, IOException {
		gerenteBlog.saveData();
	}

	public void loadData() {
		gerenteBlog.loadData();
	}

	public void cleanPersistence() {
		gerenteBlog.cleanPersistence();
	}

	public List<String> getBlogsByName(String name) {
		return gerenteBlog.getBlogPorNome(name);
	}

	public List<Blog> getBlogsBySessionId(String sessionId) {
		return gerenteBlog.getListaDeBlogsPorIdSessao(sessionId);
	}

	public void deleteBlog(Blog blog) throws PersistenceException,
			ArgumentInvalidException {
		gerenteBlog.deleteBlog(blog);
	}

	public void deleteBlog(String sessionId, String blogId)
			throws ArgumentInvalidException, PersistenceException {
		gerenteBlog.deleteBlog(sessionId, blogId);
	}

	public void warnListener(Blog blog, String id) throws UserInvalidException {
		gerenteBlog.avisaListeners(blog, id);
	}

	public String createSubBlog(String idSessao, String idBlogPai,
			String titulo, String descricao) throws ArgumentInvalidException,
			PersistenceException, IOException, UserInvalidException {
		return gerenteBlog
				.createSubBlog(idSessao, idBlogPai, titulo, descricao);
	}

	public int getNumberOfBlogsByLogin(String login)
			throws UserInvalidException {
		return gerenteBlog.getNumberOfBlogsByLogin(login);
	}

	public int getNumberOfBlogsBySessionId(String idSession)
			throws UserInvalidException, ArgumentInvalidException {
		return gerenteBlog.getNumberOfBlogsBySessionId(idSession);
	}

	public int getNumberOfSubBlogs(String blogId)
			throws ArgumentInvalidException {
		return gerenteBlog.getNumberOfSubBlogs(blogId);
	}

	public int getNumberOfAllSubBlogs(String blogId)
			throws ArgumentInvalidException {
		return gerenteBlog.getNumberOfAllSubBlogs(blogId);
	}

	public String getSubBlogId(String blogId, int index)
			throws ArgumentInvalidException {
		return gerenteBlog.getSubBlog(blogId, index);
	}

	public int getNumberOfAllPosts(String blogId)
			throws ArgumentInvalidException {
		return gerenteBlog.getNumberOfAllPosts(blogId);
	}

}
