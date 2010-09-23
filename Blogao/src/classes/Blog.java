package classes;

import java.io.UnsupportedEncodingException;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.BlogsDAO;
//VER US4.
public class Blog {
	private String titulo;
	private int id;
	private Texto descricao;
	private List<Blog> listaSubBlogs;
	private BlogsDAO blogDao;
	
	public Blog(String titulo, Texto descricao) throws ArgumentInvalidException{
		if(validaTitulo(titulo)){
			this.titulo = titulo;
			this.descricao = descricao;
			blogDao.getInstance();
		}else{
			throw new ArgumentInvalidException(codificaString("Você deve especificar um título para o blog"));
		}
		
	}
	
	/**
	 * Metodo que codifica a String para o padrao ISO.
	 * @param string
	 * @return
	 */
	private String codificaString(String string) {
		String retorno = "";
		try {
			retorno = new String(string.getBytes(), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Texto getDescricao() {
		return descricao;
	}

	public void setDescricao(Texto descricao) {
		this.descricao = descricao;
	}

	private boolean validaTitulo(String titulo) throws ArgumentInvalidException {
		if(titulo == null || titulo.trim().equals(""))
			return false;
		else
			return true;
	}

	public List<Post> listaDePosts(){
		return null ;
		
	}
	
	/**
	 * Metodo responsavel pela exclusao do blog;
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the listaSubBlogs
	 */
	public List<Blog> getListaSubBlogs() {
		return listaSubBlogs;
	}

	/**
	 * @param listaSubBlogs the listaSubBlogs to set
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
	    return getTitulo() == outra.getTitulo() && getDescricao() == outra.getDescricao();
	
	    }

	}
