package classes.func.usuario;

import java.util.List;

import interfaces.Logavel;
import classes.Blog;
import classes.Login;
import classes.Senha;

public class Usuario implements Logavel{

	private Login login;
	private Senha senha;
	private Perfil perfil;
	
	
	public Usuario(){}
	
	public Usuario(Login log, Senha sen, Perfil perfil) {
		setLogin(log);
		setSenha(sen);
		setPerfil(perfil);
	}
	
	public Usuario(Login log, Senha sen) {
		setLogin(log);
		setSenha(sen);
	}

	@Override
	public Login getLogin() {
		return login;
	}

	@Override
	public Senha getSenha() {
		return senha;
	}

	@Override
	public void setLogin(Login login) {
		this.login = login;
	}

	@Override
	public void setSenha(Senha senha) {
		this.senha = senha;
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
		this.perfil = perfil;
	}

	//CLASSE A SER IMPLEMENTADA, QUE SE RELACIONA COM O BD.
	public List<Blog> listaDeBlogs(){
		return null;
	}
	
	public String toString(){
		return login.toString();
	}

}
