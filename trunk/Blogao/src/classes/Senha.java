package classes;

import interfaces.Constantes;
import ourExceptions.ArgumentInvalidException;


/**
 * Classe que gera um objeto {@link Senha} para um determinado usuario.
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 *
 */
public class Senha {
	
	private String senha;
	
	/**
	 * Construtor da {@link Senha}
	 * @param String senha
	 * @throws ArgumentInvalidException
	 */
	public Senha(String senha) throws ArgumentInvalidException {
		setSenha(senha);
	}
	
	/**
	 * Metodo modificador de senha
	 * @param String novaSenha
	 * @throws ArgumentInvalidException
	 */
	public void setSenha(String novaSenha) throws ArgumentInvalidException {
		if (!validaSenha(novaSenha)) {
			throw new ArgumentInvalidException(Constantes.SENHA_INVALIDA);
		}
		this.senha = novaSenha;
	}
	
	/**
	 * Metodo acessador de senha
	 * @return String senha
	 */
	public String getSenha() {
		return senha;
	}
	
	/**
	 * Metodo validador que verifica a senha
	 * @param String senha
	 * @return True caso a senha seja valida
	 */
	private boolean validaSenha(String senha) {
		if (senha != null && !senha.trim().isEmpty())
            return true;
        return false;
	}
	
	/**
	 * Metodo que verifica duas senhas.
	 * @param String senha
	 * @return True caso a senha seja valida
	 */
	public boolean equals(String senha){
		if(senha == null) return false;
		return this.senha.equals(senha);
	}
	

}
