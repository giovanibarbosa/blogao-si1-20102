package classes.func.usuario;

import classes.Login;
import classes.Senha;
import interfaces.Logavel;

public class LogavelImpl implements Logavel {

	private Login login;
	private Senha senha;
	
	public LogavelImpl(Login log, Senha sen) {
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
	public void setLogin(Login log){
		this.login = log;

	}

	@Override
	public void setSenha(Senha sen) {
		this.senha = sen;


	}
	
	public String toString(){
		return login.toString();
		
	}
	
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Login)) return false;
		Login login = (Login) obj;
		return login.getLogin().equals(this.getLogin());
	}

}
