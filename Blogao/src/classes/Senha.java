package classes;

import ourExceptions.ArgumentInvalidException;


/**
 * Classe que gera um objeto Senha para um determinado usuario.
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 *
 */
public class Senha {
	
	private String senha;
	
	public Senha(String senha) throws Exception {
		setSenha(senha);
	}
	
	public void setSenha(String novaSenha) throws ArgumentInvalidException {
		if (!validaSenha(novaSenha)) {
			throw new ArgumentInvalidException("Senha inválida");
		}
		this.senha = novaSenha;
	}
	
	private boolean validaSenha(String senha) {
		if (senha != null && !senha.trim().isEmpty())
            return true;
        return false;
	}

}
