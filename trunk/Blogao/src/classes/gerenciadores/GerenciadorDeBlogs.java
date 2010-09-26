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
import classes.Post;
import classes.gerenciadores.GerenciadorDeSessoes;
import classes.func.usuario.Usuario;
import persistencia.daos.BlogsDAO;
import persistencia.daos.PostsDAO;
import persistencia.daos.UsuariosDAO;

public class GerenciadorDeBlogs implements Gerenciador{

	private static final int DESCRICAO = 1499866697;
	private static final int TITULO = -873444423;
	private static final int DONO = 3089292;

	private BlogsDAO blogsDAO = BlogsDAO.getInstance();

	private GerenciadorDeDados gerenteDados;

	private List<Blog> listaDeBlogs;
	
	public GerenciadorDeBlogs(GerenciadorDeDados gerenteDados) {
		listaDeBlogs = new ArrayList<Blog>();
		this.gerenteDados = gerenteDados;
	}

	
	
	public String createBlog(String idSessao, String titulo, String descricao)
			throws ArgumentInvalidException, PersistenceException, IOException, UserInvalidException {

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
				return gerenteDados.getGerenteSessoes().getLoginPorSessao(idSessao);
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

	public Blog getBlog(String idBlog) throws ArgumentInvalidException{
		for (Blog blog : listaDeBlogs) {
			if (blog.getId().equals(idBlog)) return blog;
		}
		throw new ArgumentInvalidException(Constantes.BLOG_INVALIDO);
	}
	
	public Blog getBlog(String idBlog, String idSessao) throws FileNotFoundException,
						PersistenceException, ArgumentInvalidException{
		Blog blog = blogsDAO.recupera(idBlog);
		if(!idSessao.equals(blog.getIdSessao())){
			throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);
		}
		return blog;
	}
	
	public int totalDeBlogsPorLogin(String login) throws ArgumentInvalidException,
						FileNotFoundException, PersistenceException, UserInvalidException{
		Usuario user = gerenteDados.getGerenteUsuarios().getUsuario(login);
		return user.getListaBlogs().size();		
	}
	
	public int totalDeBlogsPorSessao(String sessionID) throws ArgumentInvalidException,
						FileNotFoundException, PersistenceException, UserInvalidException {
		String login = gerenteDados.getGerenteSessoes().getLoginPorSessao(sessionID);
		Usuario user = gerenteDados.getGerenteUsuarios().getUsuario(login);
		return user.getListaBlogs().size();
		
	}
	
	public String recuperaIdBlogDesejado(String sessionID, int index) throws 
					FileNotFoundException, ArgumentInvalidException, PersistenceException, UserInvalidException {
		String login = gerenteDados.getGerenteSessoes().getLoginPorSessao(sessionID);
		Usuario user = gerenteDados.getGerenteUsuarios().getUsuario(login);
		return user.getListaBlogs().get(index).getId();
		
	}
	
	public String recuperaIdBlogPorLogin(String login, int index) throws FileNotFoundException,
						PersistenceException, UserInvalidException {
		Usuario user = gerenteDados.getGerenteUsuarios().getUsuario(login);
		return user.getListaBlogs().get(index).getId();
	}
	
	public int totalDePosts(String idBlog) throws FileNotFoundException,
				PersistenceException, ArgumentInvalidException {
		return getBlog(idBlog).getListaDePostagens().size();
		
	}
	
	public String recuperaIdDoPost(String idBlog, int index) throws NumberFormatException,
				FileNotFoundException, PersistenceException, ArgumentInvalidException {
		return getBlog(idBlog).getListaDePostagens().get(index).getId();
	}
//	
//	public void mudarInformacaoDoPost(String sessionID, String postID,
//			String atributo, String novoTexto) throws FileNotFoundException,
//			ArgumentInvalidException, PersistenceException {
//		Usuario user = gerenteDados.getGerenteUsuarios().recuperaUsuarioPorIdSessao(sessionID);
//		Post postRecuperado = postsDAO.recupera(postID);
//
//		for (Blog blog : user.getListaBlogs()) {
//			for (Post post : blog.getListaDePostagens()) {
//				if (post.equals(postRecuperado)) {
//					post.setAtributo(atributo, novoTexto);
//				}
//			}
//		}
//	}
//	

	/**
	 * @return the listaDeBlogs
	 */
	public List<Blog> getListaDeBlogs() {
		return listaDeBlogs;
	}

	/**
	 * @param listaDeBlogs the listaDeBlogs to set
	 */
	public void setListaDeBlogs(List<Blog> listaDeBlogs) {
		this.listaDeBlogs = listaDeBlogs;
	}

	@Override
	public void saveData() {
		
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

}
