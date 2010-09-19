package classes.func.usuario;

import java.util.List;

import interfaces.Logavel;
import classes.Blog;
import classes.Login;
import classes.Senha;

public class Usuario implements Logavel{

	private Login login;
	private Senha senha;
	
	public Usuario(){}
	
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
	
	//CLASSE A SER IMPLEMENTADA, QUE SE RELACIONA COM O BD.
	public List<Blog> listaDeBlogs(){
		return null;
	}
	
	

}
