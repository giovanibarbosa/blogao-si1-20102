package classes.func.usuario;

import ourExceptions.ArgumentInvalidException;
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
	public void setLogin(Login login) {
		try {
			login.setLogin(login.getLogin());
		} catch (ArgumentInvalidException e) {
			// TODO pensar em lancamento de excecao, ou tratamento
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setSenha(Senha senha) {
		try {
			senha.setSenha(senha.getSenha());
		} catch (ArgumentInvalidException e) {
			// TODO lancar ou tratar excecao
		}

	}
	
	public String toString(){
		return login.toString();
		
	}

}
