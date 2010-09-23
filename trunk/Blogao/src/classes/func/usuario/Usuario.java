package classes.func.usuario;

import java.util.List;

import interfaces.Logavel;
import classes.Blog;
import classes.Login;
import classes.Senha;

public class Usuario {

	private Logavel logavel;
	private Perfil perfil;
	
	public Usuario(Login log, Senha sen) {
		logavel = new LogavelImpl(log, sen); 
	}
	
	public Usuario(Login log, Senha sen, Perfil per) {
		logavel = new LogavelImpl(log, sen); 
		setPerfil(per);
	}

	public Login getLogin() {
		return logavel.getLogin();
	}
	
	public void setLogin(Login log) {
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

	//CLASSE A SER IMPLEMENTADA, QUE SE RELACIONA COM O BD.
	public List<Blog> listaDeBlogs(){
		return null;
	}
	
	public String toString(){
		return logavel.toString();
	}

}
