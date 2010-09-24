package classes;

import interfaces.Constantes;
import ourExceptions.ArgumentInvalidException;


/**
 * Classe que gera um objeto Senha para um determinado usuario.
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 *
 */
public class Senha {
	
	private String senha;
	
	public Senha(String senha) throws ArgumentInvalidException {
		try {
			setSenha(senha);
		} catch (ArgumentInvalidException e){
			throw e;
		}
	}
	
	public void setSenha(String novaSenha) throws ArgumentInvalidException {
		if (!validaSenha(novaSenha)) {
			throw new ArgumentInvalidException(Constantes.SENHA_INVALIDA);
		}
		this.senha = novaSenha;
	}
	
	public String getSenha() {
		return senha;
	}
	
	private boolean validaSenha(String senha) {
		if (senha != null && !senha.trim().isEmpty())
            return true;
        return false;
	}
	
	public boolean equals(String senha){
		if(senha == null) return false;
		return this.senha.equals(senha);
	}
	

}
