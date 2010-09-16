package classes.func.usuario;

import interfaces.Logavel;
import classes.Login;
import classes.Senha;

public class Usuario implements Logavel{

	private Login login;
	private Senha senha;
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
	
	

}
