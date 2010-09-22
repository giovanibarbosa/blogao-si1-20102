package classes;

import java.util.List;

import ourExceptions.ArgumentInvalidException;
//VER US4.
public class Blog {
	private String titulo;
	private int id;
	private Texto descricao;
	private List<Blog> listaSubBlogs;
	
	public Blog(String titulo, Texto descricao) throws ArgumentInvalidException{
		if(validaTitulo(titulo)){
			this.titulo = titulo;
			this.descricao = descricao;
		}else{
			throw new ArgumentInvalidException("Voc� deve especificar um t�tulo para o blog");
		}
		
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
		//AQUI TERA O RELACIONAMENTO COM O DAO, CAPTURANDO TODOS OS POSTS PARA ESSE BLOG.
		return null;
		
	}

	public void deleta() {
		//ESSE METODO IRA SE RELACIONAR COM O DAO.
		
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
	public boolean equals(Object objeto) {
	    if (!(objeto instanceof Blog)) {
	           return false;
	    }
	    Blog outra = (Blog) objeto;
	    return getTitulo() == outra.getTitulo() && getDescricao() == outra.getDescricao();
	
	    }

	}
