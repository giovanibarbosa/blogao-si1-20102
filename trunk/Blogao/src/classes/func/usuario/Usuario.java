package classes.func.usuario;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;

import persistencia.daos.BlogsDAO;

import interfaces.Logavel;
import classes.Blog;
import classes.Login;
import classes.Senha;

public class Usuario {

	private Logavel logavel;
	private Perfil perfil;
	private List<Blog> listaBlogs;
	
	public Usuario(Login log, Senha sen) throws Exception {
		logavel = new LogavelImpl(log, sen); 
		listaBlogs = new ArrayList<Blog>();
	}
	
	public Usuario(Login log, Senha sen, Perfil per) {
		this.logavel = new LogavelImpl(log, sen);
		setPerfil(per);
		listaBlogs = new ArrayList<Blog>();
	}

	public Login getLogin() {
		return logavel.getLogin();
	}
	
	public void setLogin(Login log) throws ArgumentInvalidException {
		logavel.setLogin(log);
	}
	
	public Senha getSenha() {
		return logavel.getSenha();
	}
	
	public void setSenha(Senha sen) {
		logavel.setSenha(sen);
	}
	/**
	 * @return the perfil
	 */
	public Perfil getPerfil() {
		return perfil;
	}

	/**
	 * @param perfil the perfil to set
	 */
	public void setPerfil(Perfil perfil) {
		if (perfil != null )
			this.perfil = perfil;
	}
	
	public void setLogavel(Logavel log) {
		this.logavel = log;
	}


	public List<Blog> listaDeBlogs() throws FileNotFoundException{
		BlogsDAO blogsDao = BlogsDAO.getInstance();
		return blogsDao.recuperaBlogs();
	}
	
	public Blog getBlog(Blog blog) throws FileNotFoundException, PersistenceException{
		BlogsDAO blogsDao = BlogsDAO.getInstance();
		return blogsDao.recupera(blog);
	}
	
	public String toString(){
		return logavel.toString();
	}
	
	/**
	 * @return the listaBlogs
	 */
	public List<Blog> getListaBlogs() {
		return listaBlogs;
	}
	
	/**
	 * Classe que adiciona um blog a lista.
	 * @param coment
	 * @throws ArgumentInvalidException 
	 */
	public void addBlog2(Blog blg) {
		if(!listaBlogs.contains(blg))
			listaBlogs.add(blg);
	}
	
	/**
	 * Remove o blog da lista.
	 * @param blg
	 * @throws ArgumentInvalidException 
	 */
	public void removeBlog2(Blog blg) {
		if(listaBlogs.contains(blg))
			listaBlogs.remove(blg);
	}

	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Usuario)) return false;
		Usuario user = (Usuario) obj;
		return getLogin().equals(user.getLogin());
	}

}
