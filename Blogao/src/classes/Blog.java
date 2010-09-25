package classes;

import interfaces.Constantes;

import java.util.ArrayList;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.BlogsDAO;
import persistencia.daos.ComentariosDAO;

//VER US4.
public class Blog {
	private String titulo;
	private String id;
	private String descricao;
	private String idSessao;
	private List<Blog> listaSubBlogs;
	private BlogsDAO blogDao = BlogsDAO.getInstance();
	private ComentariosDAO cmtDAO = ComentariosDAO.getInstance();
	private List<Integer> idsComentarios;

	public Blog(String titulo, String descricao, String idSessao)
			throws ArgumentInvalidException {
		if (validaTitulo(titulo)) {
			this.titulo = titulo;
			this.descricao = descricao;
			this.idsComentarios = new ArrayList<Integer>();
			this.listaSubBlogs = new ArrayList<Blog>();
			this.idSessao = idSessao;
			this.setId(gerarId());

		} else {
			throw new ArgumentInvalidException(Constantes.ESPECIFICA_TITULO);
		}

	}

	private String gerarId() {
		return String.valueOf(this.hashCode());
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) throws ArgumentInvalidException {
		if (titulo == null || titulo.trim().equals(""))
			throw new ArgumentInvalidException(Constantes.TITULO_INVALIDO);
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	private boolean validaTitulo(String titulo) throws ArgumentInvalidException {
		if (titulo == null || titulo.trim().isEmpty())
			return false;
		else
			return true;
	}

	public List<Post> listaDePosts() {
		return null;

	}

	/**
	 * Metodo responsavel pela exclusao do blog;
	 * 
	 * @throws PersistenceException
	 */
	public void deleta() throws PersistenceException {
		try {
			blogDao.deletar(this);
		} catch (PersistenceException e) {
			throw e;
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdSessao() {
		return idSessao;
	}

	/**
	 * @return the listaSubBlogs
	 */
	public List<Blog> getListaSubBlogs() {
		return listaSubBlogs;
	}

	/**
	 * @param listaSubBlogs
	 *            the listaSubBlogs to set
	 */
	public void setListaSubBlogs(List<Blog> listaSubBlogs) {
		this.listaSubBlogs = listaSubBlogs;
	}

	@Override
	/**
	 * Metodo que verifica a igualdade entre objetos.
	 */
	public boolean equals(Object objeto) {
		if (!(objeto instanceof Blog)) {
			return false;
		}
		Blog outra = (Blog) objeto;
		return getTitulo() == outra.getTitulo()
				&& getDescricao() == outra.getDescricao();

	}

	public void addSubBlog(Blog subblog) {
		listaSubBlogs.add(subblog);
	}

	public void removeSubBlog(Blog subblog) throws PersistenceException {
		blogDao.deletar(subblog);
		listaSubBlogs.remove(subblog);
	}

	public void addComentario(int idComentario) {
		idsComentarios.add(idComentario);
	}

	public void removeComentario(Integer idComentario)
			throws PersistenceException {
		cmtDAO.deletar(idComentario);
		idsComentarios.remove(idComentario);
	}

	@Override
	public String toString() {
		return String.valueOf(getId());
	}

}
