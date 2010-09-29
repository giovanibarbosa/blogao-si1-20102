package classes;

import interfaces.Constantes;

import java.util.ArrayList;
import java.util.List;

import ourExceptions.ArgumentInvalidException;

/**
 * Classe que inicializa um Blog.
 * 
 * @author Ana Clara Lacerda - anacls@lcc.ufcg.edu.br
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 *
 */
public class Blog {
	private String titulo;
	private String id;
	private String descricao;
	private String idSessao;
	private List<Blog> listaSubBlogs;
	private List<String> posts;
	private List<String> loginsOfModificationListeners;

	/**
	 * Construtor do Blog.
	 * 
	 * @param titulo
	 *            {@link String}
	 * @param descricao
	 *            {@link String}
	 * @param IdSessao
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 *             Caso o titulo seja invalido
	 */
	public Blog(String titulo, String descricao, String idSessao)
			throws ArgumentInvalidException {
		setTitulo(titulo);
		this.descricao = descricao;
		this.listaSubBlogs = new ArrayList<Blog>();
		this.idSessao = idSessao;
		this.id = gerarId();
		posts = new ArrayList<String>();
		loginsOfModificationListeners = new ArrayList<String>();
	}

	public void addModificationListener(String listenerLogin) {
		loginsOfModificationListeners.add(listenerLogin);
	}

	public List<String> getModificationListeners() {
		return loginsOfModificationListeners;
	}

	/**
	 * Metodo gera uma id do blog.
	 * 
	 * @return String id do blog
	 */
	private String gerarId() {
		return String.valueOf(this.hashCode());
	}

	/**
	 * Metodo acessador de titulo
	 * 
	 * @return String titulo do blog
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Metodo modificador que seta um titulo
	 * 
	 * @param titulo
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 *             caso o titulo do blog seja vazio ou null
	 */
	public void setTitulo(String titulo) throws ArgumentInvalidException {
		if (!validaTitulo(titulo))
			throw new ArgumentInvalidException(Constantes.TITULO_INVALIDO);
		this.titulo = titulo;
	}

	/**
	 * Metodo acessador de descricao
	 * 
	 * @return String descricao do blog
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Metodo modificador que seta uma descricao
	 * 
	 * @param descricao
	 *            {@link String}
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Metodo validador que verifica o titulo
	 * 
	 * @param titulo
	 *            {@link String}
	 * @return True caso o titulo seja valido
	 */
	private boolean validaTitulo(String titulo) {
		if (titulo == null || titulo.trim().isEmpty())
			return false;
		else
			return true;
	}

	/**
	 * Metodo acessador de id do blog
	 * 
	 * @return String id do blog
	 */
	public String getId() {
		return id;
	}

	/**
	 * Metodo acessador de id de sessao
	 * 
	 * @return String id da Sessao
	 */
	public String getIdSessao() {
		return idSessao;
	}

	/**
	 * Metodo acessador de lista de subBlogs
	 * 
	 * @return List<Blog> Lista de subBlogs
	 */
	public List<Blog> getListaSubBlogs() {
		return listaSubBlogs;
	}

	/**
	 * Metodo que adiciona um subBlog a um blog
	 * 
	 * @param subblog
	 *            {@link Blog}
	 */
	public void addSubBlog(Blog subblog) {
		listaSubBlogs.add(subblog);
	}

	/**
	 * Metodo acessador de postagens
	 * 
	 * @return List<String> lista de postagens
	 */
	public List<String> getListaDePostagens() {
		return posts;
	}

	/**
	 * Metodo que adiciona um post a um blog
	 * 
	 * @param postNovo
	 *            {@link Post}
	 */
	public void addPost(Post post) {
		if (!posts.contains(post.getId()))
			posts.add(post.getId());
	}

	/**
	 * Metodo que remove um post do blog
	 * 
	 * @param post
	 *            {@link Post}
	 */
	public void removePost(Post post) {
		if (posts.contains(post))
			posts.remove(post);
	}

	/**
	 * Metodo modificador que seta uma lista de posts
	 * 
	 * @param listaDePosts
	 *            ArrayList<{@link String}>
	 */
	public void setlistaDePosts(ArrayList<String> listaDePosts) {
		posts = listaDePosts;

	}

	/**
	 * Metodo que retorna o numero de posts de um Blog
	 * 
	 * @return int numero de posts
	 */
	public int getNumeroDePosts() {
		return posts.size();
	}

	@Override
	public String toString() {
		return getId();
	}

	/**
	 * Metodo que verifica a igualdade entre objetos Blog.
	 * 
	 * @return True caso os objetos sejam iguais
	 */
	@Override
	public boolean equals(Object objeto) {
		if (!(objeto instanceof Blog)) {
			return false;
		}
		Blog outra = (Blog) objeto;
		return getId().equals(outra.getId());
	}

	public void removePost2(Post post) {
		posts.remove(post);

	}

}
