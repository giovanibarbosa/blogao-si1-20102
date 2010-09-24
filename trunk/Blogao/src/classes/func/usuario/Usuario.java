package classes.func.usuario;

import java.util.ArrayList;
import java.util.List;

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
	
	public Usuario(Login log, Senha sen, Perfil per) throws Exception {
		this.logavel = new LogavelImpl(log, sen);
		setPerfil(per);
		listaBlogs = new ArrayList<Blog>();
	}

	public Login getLogin() {
		return logavel.getLogin();
	}
	
	public void setLogin(Login log) throws Exception {
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

	//CLASSE A SER IMPLEMENTADA, QUE SE RELACIONA COM O BD.
	public List<Blog> listaDeBlogs(){
		return null;
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

	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Usuario)) return false;
		Usuario user = (Usuario) obj;
		return user.getLogin().equals(this.getLogin());
	}

}
