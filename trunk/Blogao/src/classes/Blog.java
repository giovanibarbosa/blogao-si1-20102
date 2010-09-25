package classes;

import interfaces.Constantes;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import classes.func.usuario.Usuario;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.BlogsDAO;
import persistencia.daos.ComentariosDAO;

//VER US4.
public class Blog {
	private String titulo;
	private String id;
	private String descricao;
	private String dono;
	private List<Blog> listaSubBlogs;
	private BlogsDAO blogDao = BlogsDAO.getInstance();
	private ComentariosDAO cmtDAO = ComentariosDAO.getInstance();
	private List<Integer> idsComentarios;
	
	private static final int DESCRICAO = 1499866697;
	private static final int TITULO = -873444423;
	private static final int DONO = 3089292;

	public Blog(String titulo, String descricao, String dono) throws ArgumentInvalidException {
		if (validaTitulo(titulo)) {
			this.titulo = titulo;
			this.descricao = descricao;
			this.idsComentarios = new ArrayList<Integer>();
			this.listaSubBlogs = new ArrayList<Blog>();
			this.dono = dono;
			this.setId(gerarId());
			
		} else {
			throw new ArgumentInvalidException(
					Constantes.ESPECIFICA_TITULO);
		}

	}


	private String gerarId() {
		return String.valueOf(this.hashCode());
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
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
	public void deleta() {
		try {
			blogDao.deletar(this);
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public void addSubBlog(Blog subblog){
		listaSubBlogs.add(subblog);
	}
	
	public void removeSubBlog(Blog subblog) throws PersistenceException{
		blogDao.deletar(subblog);
		listaSubBlogs.remove(subblog);
	}
	
	public void addComentario(int idComentario){
		idsComentarios.add(idComentario);
	}
	
	public void removeComentario(Integer idComentario) throws PersistenceException{
		cmtDAO.deletar(idComentario);
		idsComentarios.remove(idComentario);
	}

	public String toString() {
		return String.valueOf(getId());
	}
	
	public String getAtributo(String atributo) throws ArgumentInvalidException {
		if (atributo == null || atributo.equals(""))
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO2);
		
		int codigoAtributo = atributo.hashCode();
		
		switch(codigoAtributo) {
			
			case(TITULO):
				return this.titulo.toString();
		
			case(DESCRICAO):
				return this.descricao.toString();
			
			case(DONO):
				return this.dono;
			default:
				throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO2);
		}
				
	}
	
}
