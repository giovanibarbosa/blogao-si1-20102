package classes.func.blog;

import interfaces.Constantes;

import java.util.ArrayList;
import java.util.List;

import classes.Post;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.BlogInvalidException;

/**
 * Classe que inicializa um NewBlog.
 * 
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 *
 */
public class NewBlog {
	private String titulo;
	private String id;
	private String descricao;
	private String idSessao;
	private List<NewBlog> listaSubNewBlogs;
	private List<String> posts;
	private List<String> loginsOfModificationListeners;

	/**
	 * Construtor do NewBlog.
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
	public NewBlog(String titulo, String descricao, String idSessao)
			throws ArgumentInvalidException {
		setTitulo(titulo);
		this.descricao = descricao;
		this.listaSubNewBlogs = new ArrayList<NewBlog>();
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
	 * Metodo gera uma id do NewBlog.
	 * 
	 * @return String id do NewBlog
	 */
	private String gerarId() {
		return String.valueOf(this.hashCode());
	}

	/**
	 * Metodo acessador de titulo
	 * 
	 * @return String titulo do NewBlog
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
	 *             caso o titulo do NewBlog seja vazio ou null
	 */
	public void setTitulo(String titulo) throws ArgumentInvalidException {
		if (!validaString(titulo))
			throw new ArgumentInvalidException(Constantes.TITULO_INVALIDO);
		this.titulo = titulo;
	}

	/**
	 * Metodo acessador de descricao
	 * 
	 * @return String descricao do NewBlog
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Metodo modificador que seta uma descricao
	 * 
	 * @param descricao
	 *            {@link String}
	 * @throws ArgumentInvalidException 
	 */
	public void setDescricao(String descricao) throws ArgumentInvalidException {
		if (!validaString(descricao)) {
			throw new ArgumentInvalidException(Constantes.DESCRICAO_INVALIDA);
		}
		this.descricao = descricao;
	}

	/**
	 * Metodo validador que verifica o titulo
	 * 
	 * @param titulo
	 *            {@link String}
	 * @return True caso o titulo seja valido
	 */
	private boolean validaString(String titulo) {
		if (titulo == null || titulo.trim().isEmpty())
			return false;
		else
			return true;
	}

	/**
	 * Metodo acessador de id do NewBlog
	 * 
	 * @return String id do NewBlog
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
	 * Metodo acessador de lista de subNewBlogs
	 * 
	 * @return List<NewBlog> Lista de subNewBlogs
	 */
	public List<NewBlog> getListaSubNewBlogs() {
		return listaSubNewBlogs;
	}

	/**
	 * Metodo que adiciona um subNewBlog a um NewBlog
	 * 
	 * @param subNewBlog
	 *            {@link NewBlog}
	 * @throws BlogInvalidException 
	 * @throws ArgumentInvalidException 
	 */
	public void addSubNewBlog(NewBlog subNewBlog) throws BlogInvalidException, ArgumentInvalidException {
		if(subNewBlog == null) {
			throw new BlogInvalidException(Constantes.BLOG_INVALIDO);
		}
		if (listaSubNewBlogs.contains(subNewBlog)) {
			throw new ArgumentInvalidException(Constantes.BLOG_EXISTENTE);
		}
		listaSubNewBlogs.add(subNewBlog);
	}
	
	public void removeSubNewBlog(NewBlog subNewBlog) throws ArgumentInvalidException, BlogInvalidException {
		if(subNewBlog == null) {
			throw new BlogInvalidException(Constantes.BLOG_INVALIDO);
		}
		if (!listaSubNewBlogs.contains(subNewBlog)) {
			throw new ArgumentInvalidException(Constantes.BLOG_INEXISTENTE);
		}
		listaSubNewBlogs.remove(subNewBlog);
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
	 * Metodo que adiciona um post a um NewBlog
	 * 
	 * @param postNovo
	 *            {@link Post}
	 */
	public void addPost(Post post) {
		if (!posts.contains(post.getId()))
			posts.add(post.getId());
	}

	/**
	 * Metodo que remove um post do NewBlog
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
	 * Metodo que retorna o numero de posts de um NewBlog
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
	 * Metodo que verifica a igualdade entre objetos NewBlog.
	 * 
	 * @return True caso os objetos sejam iguais
	 */
	@Override
	public boolean equals(Object objeto) {
		if (!(objeto instanceof NewBlog)) {
			return false;
		}
		NewBlog outra = (NewBlog) objeto;
		return getId().equals(outra.getId());
	}

	public void removePost2(Post post) {
		posts.remove(post);

	}
	
	public int getTotalSubNewBlogs() {
		return listaSubNewBlogs.size();
	}

}
