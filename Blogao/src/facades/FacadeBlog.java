package facades;

import java.util.List;

import classes.Blog;
import classes.gerenciadores.GerenciadorDeBlogs;
import classes.gerenciadores.GerenciadorDeDados;

public class FacadeBlog {
	
	//TODO consertar
	private GerenciadorDeBlogs gerenteBlog = new GerenciadorDeBlogs
								(GerenciadorDeDados.getInstence());
	
	
	public String createBlog(String idSessao, String titulo, String descricao) {
		return gerenteBlog.createBlog(idSessao, titulo, descricao);		
	}
	
	public Blog getBlogByIdBlog(String idBlog) {
		return gerenteBlog.getBlog(idBlog);		
	}

	public Blog getBlogByIdBlogAndIdSession(String idBlog, String idSessao) {
		return gerenteBlog.getBlog(idBlog, idSessao);
	}
	
	public List<Blog> getBlogsByLogin(String login) {
		return gerenteBlog.getBlogPorLogin(login);		
	}
	
	public int totalBlogsByLogin(String login) {
		return gerenteBlog.totalDeBlogsPorLogin(login);
	}
	
	public int totalBlogsBySessionId(String sessionId) {
		return gerenteBlog.totalDeBlogsPorSessao(sessionId);
	}
	
	public String getIdBlogBySessionIdAndIndex(String sessionId, int index) {
		return gerenteBlog.recuperaIdBlogDesejado(sessionId, index);
	}
	
	public String getIdBlogByLoginAndIndex(String login, int index) {
		return gerenteBlog.recuperaIdBlogPorLogin(login, index);		
	}
	
	public int totalPostsByIdBlog(String blogId) {
		return gerenteBlog.totalDePosts(blogId);
	}
	
	public String getIdPost(String blogId, int index) {
		return gerenteBlog.recuperaIdDoPost(blogId, index);		
	}
	
	public String getAtribute(Blog blog, String atribute) {
		return gerenteBlog.getAtributo(blog, atribute);
	}
	
	public Blog changeBlogInformation(Blog blog, String atributo,
			String novoValor) {
		return gerenteBlog.changeBlogInformation(blog, atributo, novoValor);
	}
	
	public void changeBlogInformation(String idSessao, String idBlog,
			String atributo, String novoValor) {
		gerenteBlog.changeBlogInformation(idSessao, idBlog, atributo, novoValor);
	}
	
	public void validateBlogOwner(Blog blog, String sessionId) {
		gerenteBlog.validaDonoBlog(blog, sessionId);
	}
	
	public String getBlogInformation(String idBlog, String atributo) {
		return gerenteBlog.getBlogInformation(idBlog, atributo);		
	}
	
	public List<Blog> getListBlogs() {
		return gerenteBlog.getListaDeBlogs();
	}
	
	public void saveData() {
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
	
	public void deleteBlog(Blog blog) {
		gerenteBlog.deleteBlog(blog);
	}
	
	public void deleteBlog(String sessionId, String blogId) {
		gerenteBlog.deleteBlog(sessionId, blogId);
	}
	
	public void warnListener(Blog blog, String id) {
		gerenteBlog.avisaListeners(blog, id);		
	}
	
	public String createSubBlog(String idSessao, String idBlogPai,
			String titulo, String descricao) {
		return gerenteBlog.createSubBlog(idSessao, idBlogPai, titulo, descricao);
	}
	
	public int getNumberOfBlogsByLogin(String login) {
		return gerenteBlog.getNumberOfBlogsByLogin(login);
	}
	
	public int getNumberOfBlogsBySessionId(String idSession) {
		return gerenteBlog.getNumberOfBlogsBySessionId(idSession);
	}
	
	public int getNumberOfSubBlogs(String blogId) {
		return gerenteBlog.getNumberOfSubBlogs(blogId);
	}
	
	public int getNumberOfAllSubBlogs(String blogId) {
		return gerenteBlog.getNumberOfAllSubBlogs(blogId);
	}
	
	public String getSubBlogId(String blogId, int index) {
		return gerenteBlog.getSubBlog(blogId, index);
	}
	
	public int getNumberOfAllPosts(String blogId) {
		return gerenteBlog.getNumberOfAllPosts(blogId);
	}
	
	
}
