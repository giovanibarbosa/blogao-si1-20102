package classes.func.usuario;

import classes.Login;
import classes.Senha;
import interfaces.Logavel;

/**
 * Classe que implementa a classe {@link Logavel}
 * 
 * @author Ana Clara Lacerda - anacls@lcc.ufcg.edu.br
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 * 
 */
public class LogavelImpl implements Logavel {

	private Login login;
	private Senha senha;
	
	/**
	 * Construtor do objeto Lovavel
	 * @param log {@link Login}(Senha)
	 * @param sen {@link Senha}(Login)
	 */
	public LogavelImpl(Login log, Senha sen) {
		setLogin(log);
		setSenha(sen);
	}
	
	/**
	 * Metodo acessador de login
	 * @return {@link Login}
	 */
	@Override
	public Login getLogin() {
		return login;
	}

	/**
	 * Metodo acessador de Senha
	 * @return {@link Senha}
	 */
	@Override
	public Senha getSenha() {
		return senha;
	}

	/**
	 * Metodo modificador de login
	 * @param log {@link Login}
	 */
	@Override
	public void setLogin(Login log){
		this.login = log;

	}

	/**
	 * Metodo modificador que seta uma nova senha
	 * @param sen {@link Senha}
	 */
	@Override
	public void setSenha(Senha sen) {
		this.senha = sen;


	}
	
	@Override
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
