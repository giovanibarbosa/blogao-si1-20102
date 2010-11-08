package classes.gerenciadores;

import interfaces.Gerenciador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import classes.Blog;
import classes.Login;
import classes.Post;
import classes.Sessao;
import classes.func.usuario.Usuario;
import enuns.Constantes2;
import persistencia.daos.BlogsDAO;

/**
 * Classe que gerencia os blogs ({@link Blog})
 * 
 * 
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br 
 * 
 */
public class GerenciadorDeBlogs implements Gerenciador {

	private BlogsDAO blogsDAO = BlogsDAO.getInstance();

	private GerenciadorDeDados gerenteDados;

	private List<Blog> listaDeBlogs;
	private List<Blog> listaDeSubBlogs;

	private static final int DESCRICAO = 1499866697;
	private static final int TITULO = -873444423;
	private static final int DONO = 3089292;

	/**
	 * Contrutor para este gerenciador
	 * 
	 * @param gerenteDados
	 *            {@link GerenciadorDeDados}
	 */
	public GerenciadorDeBlogs(GerenciadorDeDados gerenteDados) {
		listaDeBlogs = new ArrayList<Blog>();
		listaDeSubBlogs = new ArrayList<Blog>();
		this.gerenteDados = gerenteDados;
	}

	@Override
	public void saveData() throws PersistenceException, IOException {
		blogsDAO.limparBlogs();
		Iterator<Blog> it = iteradorBlog();
		while(it.hasNext()){
			blogsDAO.criar( it.next());
		}
	
	}

	@Override
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
		listaDeBlogs = new ArrayList<Blog>();
	
	}

	/**
	 * Metodo que recupera um {@link Blog}
	 * 
	 * @param idBlog
	 *            {@link String}
	 * @return O {@link Blog}
	 * @throws ArgumentInvalidException
	 */
	public Blog getBlog(String idBlog) throws ArgumentInvalidException {
		Iterator<Blog> it = iteradorBlog();
		while(it.hasNext()){
			Blog blog = it.next();
			if (blog.getId().equals(idBlog))
				return blog;
		}
		Iterator<Blog> itSub = iteradorSubBlog();
		while(itSub.hasNext()){
			Blog subBlog = itSub.next();
			if (subBlog.getId().equals(idBlog))
				return subBlog;
		}
		throw new ArgumentInvalidException(Constantes2.BLOG_INVALIDO.getName());
	}

	/**
	 * Metodo que recupera um {@link Blog}
	 * 
	 * @param idBlog
	 *            {@link String}
	 * @param idSessao
	 *            {@link String}
	 * @return O {@link Blog}
	 * @throws FileNotFoundException
	 * @throws PersistenceException
	 * @throws ArgumentInvalidException
	 */
	public Blog getBlog(String idBlog, String idSessao)
			throws FileNotFoundException, PersistenceException,
			ArgumentInvalidException {
		Blog blog = getBlog(idBlog);
		if (!idSessao.equals(blog.getIdSessao())) {
			throw new ArgumentInvalidException(Constantes2.SESSAO_INVALIDA.getName());
		}
		return blog;
	}

	/**
	 * Muda as informações de um {@link Blog}
	 * 
	 * @param blog
	 *            {@link String}
	 * @param atributo
	 *            {@link String}
	 * @param novoValor
	 *            {@link String}
	 * @return {@link Blog}
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 * @throws IOException
	 */
	public Blog changeBlogInformation(Blog blog, String atributo,
			String novoValor) throws ArgumentInvalidException,
			PersistenceException, IOException {
	
		if (atributo == null)
			throw new ArgumentInvalidException(Constantes2.ATRIBUTO_INVALIDO2.getName());
	
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
			throw new ArgumentInvalidException(Constantes2.ATRIBUTO_INVALIDO2.getName());
		}
	
	}

	/**
	 * Método que recupera blogs ({@link Blog}) por um {@link Login}
	 * 
	 * @param login
	 *            {@link String}
	 * @return Uma {@link List} de blogs ({@link Blog})
	 * @throws UserInvalidException
	 */
	public List<Blog> getBlogPorLogin(String login) throws UserInvalidException {
		Usuario user = getUserByLogin(login);
		return user.getListaBlogs();
	}

	/**
	 * @return the listaDeBlogs
	 */
	public List<Blog> getListaDeBlogs() {
		return listaDeBlogs;
	}

	/**
	 * Recupera blogs ({@link Blog})
	 * 
	 * @param idSessao
	 *            {@link String}
	 * @return blogs ({@link Blog})
	 */
	public List<Blog> getListaDeBlogsPorIdSessao(String idSessao) {
		List<Blog> listaBlogsComIdSessaoBuscado = new ArrayList<Blog>();
		Iterator<Blog> it = iteradorBlog();
		while(it.hasNext()){
			Blog blog = (Blog) it.next();
			if (blog.getIdSessao().equals(idSessao)) {
				listaBlogsComIdSessaoBuscado.add(blog);
			}
		}
		return listaBlogsComIdSessaoBuscado;
	}
		/**
		 * Recupera blogs ({@link Blog})
		 * 
		 * @param match
		 *            {@link String}
		 * @return blogs ({@link Blog})
		 */
		public List<String> getBlogPorNome(String match) {
			List<String> listaBlog = new ArrayList<String>();
			Iterator<Blog> it = iteradorBlog();
			while(it.hasNext()){
				Blog blog = it.next();
				if (titulosEquivalentes(match, blog))
					listaBlog.add(blog.getId());
			}
		return listaBlog;
	}

	/**
	 * Muda as informações de um {@link Blog}
	 * 
	 * @param idSessao
	 *            {@link String}
	 * @param idBlog
	 *            {@link String}
	 * @param atributo
	 *            {@link String}
	 * @param novoValor
	 *            {@link String}
	 * @throws Exception 
	 */
	public void changeBlogInformation(String idSessao, String idBlog,
			String atributo, String novoValor) throws Exception {
	
		String login = getLogin(idSessao);
	
		if (!usuarioLogado(login)) {
			throw new ArgumentInvalidException(Constantes2.SESSAO_INVALIDA.getName());
		}
	
		Usuario us = getUser(login);
		if (verificaExistenciaDeBlog(idBlog)) {
			Blog blog = getBlog(idBlog);
			validaDonoBlog(blog, idSessao);
			us.removeBlog(blog);
			us.addBlog(changeBlogInformation(blog, atributo, novoValor));
		}
	
	}

	/**
	 * Valida o dono de um {@link Blog}
	 * 
	 * @param blog
	 *            {@link String}
	 * @param idSessao
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 */
	public void validaDonoBlog(Blog blog, String idSessao)
			throws ArgumentInvalidException {
		if (!blog.getIdSessao().equals(idSessao)) {
			throw new ArgumentInvalidException(Constantes2.SESSAO_INVALIDA.getName());
		}	
	}

	/**
	 * Deleta um {@link Blog}
	 * 
	 * @param blog
	 *            {@link Stting}
	 * @throws PersistenceException
	 * @throws ArgumentInvalidException
	 */
	public void deleteBlog(Blog blog) throws PersistenceException,
			ArgumentInvalidException {
		List<Post> postsAApagar = getPostsDel(blog);
		while (!postsAApagar.isEmpty()) {
			apagaPost(postsAApagar);
			postsAApagar.remove(0);
		}
		listaDeBlogs.remove(blog);
	}

	/**
	 * Deleta um {@link Blog}
	 * 
	 * @param sessionId
	 *            {@link Stting}
	 * @param blogId
	 *            {@link Stting}
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 */
	public void deleteBlog(String sessionId, String blogId)
			throws ArgumentInvalidException, PersistenceException {
		Blog blog = getBlog(blogId);
		validaDonoBlog(blog, sessionId);
		deleteBlog(blog);

	}

	/**
	 * Avisa a um listener
	 * 
	 * @param blog
	 *            {@link String}
	 * @param id
	 *            {@link String}
	 * @throws UserInvalidException
	 */
	public void avisaListeners(Blog blog, String id)
			throws UserInvalidException {
		for (String login : blog.getModificationListeners()) {
			getUser(login).addAviso(
					blog, id);
		}

	}

	/**
	 * Metodo que cria um {@link Blog}
	 * 
	 * @param idSessao
	 *            {@link String}
	 * @param titulo
	 *            {@link String}
	 * @param descricao
	 *            {@link String}
	 * @return O id do blog criado na forma de {@link String}
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 * @throws IOException
	 * @throws UserInvalidException
	 */
	public String createBlog(String idSessao, String titulo, String descricao)
			throws Exception {
	
		String login = getLogin(idSessao);
		Blog blog = new Blog(titulo, descricao, idSessao);
	
		Usuario us = getUser(login);
		us.addBlog(blog);
		listaDeBlogs.add(blog);
		return blog.getId();
	
	}

	/**
	 * Recupera o id {@link String} do Blog
	 * 
	 * @param sessionID
	 *            {@link String}
	 * @param index
	 *            {@link String}
	 * @return O id do Blog {@link String}
	 * @throws FileNotFoundException
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 * @throws UserInvalidException
	 */
	public String recuperaIdBlogDesejado(String sessionID, int index)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException, UserInvalidException {
	
		int indexAtual = 0;
		Iterator<Blog> it = iteradorBlog();
		while(it.hasNext()){
			Blog blog = it.next();
			if (blog.getIdSessao().equals(sessionID)) {
				if (indexAtual == index) {
					return blog.getId();
				} else {
					indexAtual++;
				}
	
			}
		}
		throw new ArgumentInvalidException(Constantes2.ATRIBUTO_INVALIDO.getName());
	
	}

	/**
	 * Recupera o id de um {@link Blog} por {@link Login}
	 * 
	 * @param login
	 *            {@link String}
	 * @param index
	 *            {@link String}
	 * @return O id de um {@link Blog} por {@link Login}
	 * @throws FileNotFoundException
	 * @throws PersistenceException
	 * @throws UserInvalidException
	 * @throws ArgumentInvalidException
	 */
	public String recuperaIdBlogPorLogin(String login, int index)
			throws FileNotFoundException, PersistenceException,
			UserInvalidException, ArgumentInvalidException {
		String idSession = String.valueOf(login.hashCode());
		return recuperaIdBlogDesejado(idSession, index);
	}

	/**
	 * Recupera o id {@link String} de um {@link Post}
	 * 
	 * @param idBlog
	 *            {@link String}
	 * @param index
	 *            {@link String}
	 * @return O id {@link String} de um {@link Post}
	 * @throws NumberFormatException
	 * @throws FileNotFoundException
	 * @throws PersistenceException
	 * @throws ArgumentInvalidException
	 */
	public String recuperaIdDoPost(String idBlog, int index)
			throws NumberFormatException, FileNotFoundException,
			PersistenceException, ArgumentInvalidException {
		return getIdPost(idBlog, index);
	}

	/**
	 * Recupera um atributo de um {@link Blog}
	 * 
	 * @param b
	 *            {@link Blog}
	 * @param atributo
	 *            {@link String}
	 * @return Um atributo de um {@link Blog}
	 * @throws ArgumentInvalidException
	 */
	public String getAtributo(Blog b, String atributo)
			throws ArgumentInvalidException {
		if (atributo == null || atributo.equals(""))
			throw new ArgumentInvalidException(Constantes2.ATRIBUTO_INVALIDO2.getName());
	
		int codigoAtributo = atributo.hashCode();
	
		switch (codigoAtributo) {
	
		case (TITULO):
			return b.getTitulo().toString();
	
		case (DESCRICAO):
			return b.getDescricao().toString();
	
		case (DONO):
			String idSessao = b.getIdSessao();
			return getLogin(idSessao);
		default:
			throw new ArgumentInvalidException(Constantes2.ATRIBUTO_INVALIDO2.getName());
		}
	
	}

	/**
	 * Recupera uma informação de um {@link Blog}
	 * 
	 * @param idBlog
	 *            {@link String}
	 * @param atributo
	 *            {@link String}
	 * @return A informação de um {@link Blog}
	 * @throws ArgumentInvalidException
	 * @throws FileNotFoundException
	 * @throws PersistenceException
	 */
	public String getBlogInformation(String idBlog, String atributo)
			throws ArgumentInvalidException, FileNotFoundException,
			PersistenceException {
	
		Blog blog = getBlogById(idBlog);
		return getInformation(atributo, blog);
	
	}

	/**
	 * Cria um subBlog {@link Blog}
	 * 
	 * @param idSessao
	 *            {@link Stting}
	 * @param idBlogPai
	 *            {@link Stting}
	 * @param titulo
	 *            {@link Stting}
	 * @param descricao
	 *            {@link Stting}
	 * @return o id de um {@link Blog}
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 * @throws IOException
	 * @throws UserInvalidException
	 */
	public String createSubBlog(String idSessao, String idBlogPai,
			String titulo, String descricao) throws ArgumentInvalidException,
			PersistenceException, IOException, UserInvalidException {

		Blog blogPai = getPaiDoBlog(idSessao, idBlogPai);
		Blog subBlog = new Blog(titulo, descricao, idSessao);
		blogPai.addSubBlog(subBlog);
		listaDeSubBlogs.add(subBlog);
		return subBlog.getId();
	}

	/**
	 * Recupera o id de um sub blog ({@link Blog})
	 * 
	 * @param blogId
	 *            {@link String}
	 * @param index
	 *            {@link String}
	 * @return O id de um sub blog ({@link Blog})
	 * @throws ArgumentInvalidException
	 */
	public String getSubBlog(String blogId, int index)
			throws ArgumentInvalidException {
		Blog blog = getBlog(blogId);
		if (index >= blog.getListaSubBlogs().size())
			throw new ArgumentInvalidException(Constantes2.INDICE_INVALIDO.getName());
		return subBlog(index, blog);
	}

	/**
	 * Recupera o numero de blogs por um {@link Login}
	 * 
	 * @param login
	 *            {@link String}
	 * @return O numero de blogs por um {@link Login}
	 * @throws UserInvalidException
	 */
	public int getNumberOfBlogsByLogin(String login)
			throws UserInvalidException {
		Usuario us = getUser(login);
		return us.getListaBlogs().size();
	}

	/**
	 * Recupera o numero de blogs por uma {@link Sessao}
	 * 
	 * @param idSession
	 *            {@link String}
	 * @return O numero de blogs por uma {@link Sessao}
	 * @throws UserInvalidException
	 * @throws ArgumentInvalidException
	 */
	public int getNumberOfBlogsBySessionId(String idSession)
			throws UserInvalidException, ArgumentInvalidException {
		String login = getLogin(idSession);
		return getNumberOfBlogsByLogin(login);
	}

	/**
	 * Recupera o numero de sub-blogs de um {@link Blog}
	 * 
	 * @param blogId
	 *            {@link String}
	 * @return O numero de sub-blogs de um {@link Blog}
	 * @throws ArgumentInvalidException
	 */
	public int getNumberOfSubBlogs(String blogId)
			throws ArgumentInvalidException {
		Blog blog = getBlog(blogId);
		return blog.getListaSubBlogs().size();
	}

	/**
	 * Recupera o numero de todos os sub-blogs de um {@link Blog}
	 * 
	 * @param blogId
	 *            {@link String}
	 * @return O numero de todos os sub-blogs de um {@link Blog}
	 * @throws ArgumentInvalidException
	 */
	public int getNumberOfAllSubBlogs(String blogId)
			throws ArgumentInvalidException {
		Blog blog = getBlog(blogId);
		int total = 0;
		for (Blog subBlog : blog.getListaSubBlogs()) {
			total++;
			total += getNumberOfAllSubBlogs(subBlog.getId());
		}
		return total;
	}
	
		/**
	 * Recupera o numero de todos os posts de um {@link Blog}
	 * 
	 * @param blogId
	 *            {@link String}
	 * @return O numero de todos os posts de um {@link Blog}
	 * @throws ArgumentInvalidException
	 */
	public int getNumberOfAllPosts(String blogId)
			throws ArgumentInvalidException {
		Blog blog = getBlog(blogId);
		int total = 0;
		for (Blog subBlog : blog.getListaSubBlogs()) {
			total += getNumberOfAllPosts(subBlog.getId());
		}
		return total + blog.getListaDePostagens().size();
	}

	/**
	 * Recupera o total de blogs ({@link Blog}) por {@link Login}
	 * 
	 * @param login
	 *            {@link String}
	 * @return Um inteiro com a quantidade de blogs
	 * @throws ArgumentInvalidException
	 * @throws FileNotFoundException
	 * @throws PersistenceException
	 * @throws UserInvalidException
	 */
	public int totalDeBlogsPorLogin(String login)
			throws ArgumentInvalidException, FileNotFoundException,
			PersistenceException, UserInvalidException {
		Usuario user = getUserByLogin(login);
		return user.getListaBlogs().size();
	}

	/**
	 * Recupera o total de blogs ({@link Blog}) por {@link Sessao}
	 * 
	 * @param sessionID
	 *            {@link String}
	 * @return Um inteiro com a quantidade de blogs
	 * @throws ArgumentInvalidException
	 * @throws FileNotFoundException
	 * @throws PersistenceException
	 * @throws UserInvalidException
	 */
	public int totalDeBlogsPorSessao(String sessionID)
			throws ArgumentInvalidException, FileNotFoundException,
			PersistenceException, UserInvalidException {
		int contador = 0;
		Iterator<Blog> it = iteradorBlog();
		while(it.hasNext()){
			Blog blog = it.next();
			if(blog.getIdSessao().equals(sessionID)) contador++;
		}
		return contador;
	}

	/**
	 * Recupera o total de posts {@link Post} de um {@link Blog}
	 * 
	 * @param idBlog
	 *            {@link String}
	 * @return O total de posts {@link Post} de um {@link Blog}
	 * @throws FileNotFoundException
	 * @throws PersistenceException
	 * @throws ArgumentInvalidException
	 */
	public int totalDePosts(String idBlog) throws FileNotFoundException,
			PersistenceException, ArgumentInvalidException {
		return getBlog(idBlog).getListaDePostagens().size();
	
	}

		private Usuario getUserByLogin(String login) throws UserInvalidException {
		return gerenteDados.getGerenteUsuarios().getUsuario(login);
	}

	private boolean titulosEquivalentes(String match, Blog blog) {
		return blog.getTitulo().toLowerCase().contains(match.toLowerCase());
	}

	private Usuario getUser(String login) throws UserInvalidException {
		return gerenteDados.getGerenciadorDeUsuarios().getUsuario(login);
	}

	private boolean usuarioLogado(String login) throws Exception {
		return gerenteDados.getGerenteSessoes().isUserLogged(login);
	}

	private String getLogin(String idSessao) throws ArgumentInvalidException {
		return gerenteDados.getGerenteSessoes().getLoginPorSessao(
				idSessao);
	}

		private void apagaPost(List<Post> postsAApagar)
			throws PersistenceException, ArgumentInvalidException {
		gerenteDados.getGerentePosts().removePost(
				postsAApagar.get(0).getId());
	}

	private List<Post> getPostsDel(Blog blog) {
		return gerenteDados.getGerentePosts()
				.getListaPostsPorBlog(blog);
	}

		private String getIdPost(String idBlog, int index)
			throws ArgumentInvalidException {
		return getBlog(idBlog).getListaDePostagens().get(index);
	}

	private String getInformation(String atributo, Blog blog)
			throws ArgumentInvalidException {
		return gerenteDados.getGerenteBlogs().getAtributo(blog, atributo);
	}

	private Blog getBlogById(String idBlog) throws ArgumentInvalidException {
		return gerenteDados.getGerenteBlogs().getBlog(idBlog);
	}

	private Blog getPaiDoBlog(String idSessao, String idBlogPai)
			throws FileNotFoundException, PersistenceException,
			ArgumentInvalidException {
		return gerenteDados.getGerenteBlogs().getBlog(idBlogPai,
				idSessao);
	}

		private String subBlog(int index, Blog blog) {
		return blog.getListaSubBlogs().get(index).getId();
	}

		/**
		 * Verifica se um {@link Blog} existe a partir de um id
		 * 
		 * @param idBlog
		 *            {@link String}
		 * @return true ou false
		 * @throws ArgumentInvalidException
		 */
		private boolean verificaExistenciaDeBlog(String idBlog)
				throws ArgumentInvalidException {
			Iterator<Blog> it = iteradorBlog();
			while(it.hasNext()){
				Blog blog = it.next();
				if (blog.getId().equals(idBlog)) {
					return true;
				}
			}
			throw new ArgumentInvalidException(Constantes2.BLOG_INVALIDO.getName());
		}

		public void atualizaBlog(Blog blog) throws PersistenceException, IOException {
			blogsDAO.atualizar(blog);
		}
		
		
		/**
		 * Iterador sobre a lista de Blogs.
		 * @return Iterator<Blog>
		 */
		public Iterator<Blog> iteradorBlog(){
			return new Iterator<Blog>() {
				private int cursor = 0;


				@Override
				public boolean hasNext() {
					while(cursor < listaDeBlogs.size()) {
						if(listaDeBlogs.get(cursor) instanceof Blog)
							return true;
						cursor++;
					}				
					return false;
				}


				@Override
				public Blog next() {
					try {
						Blog b = listaDeBlogs.get(cursor);
						if(b instanceof Blog) {
							cursor++;
							return b;
						}
						cursor++;
					} catch (NoSuchElementException e) {
						throw e;
					}
					return next();
				}


				@Override
				public void remove() {				
				}
			};
		}
		
		/**
		 * Iterador sobre a lista de SubBlogs.
		 * @return Iterator<Blog>
		 */
		public Iterator<Blog> iteradorSubBlog(){
			return new Iterator<Blog>() {
				private int cursor = 0;


				@Override
				public boolean hasNext() {
					while(cursor < listaDeSubBlogs.size()) {
						if(listaDeSubBlogs.get(cursor) instanceof Blog)
							return true;
						cursor++;
					}				
					return false;
				}


				@Override
				public Blog next() {
					try {
						Blog b = listaDeSubBlogs.get(cursor);
						if(b instanceof Blog) {
							cursor++;
							return b;
						}
						cursor++;
					} catch (NoSuchElementException e) {
						throw e;
					}
					return next();
				}


				@Override
				public void remove() {				
				}
			};
		}

}
