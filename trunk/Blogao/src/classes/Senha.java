package classes;

import enuns.Constantes;
import ourExceptions.ArgumentInvalidException;

/**
 * Classe que gera um objeto {@link Senha} para um determinado usuario.
 * 
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 * 
 */
public class Senha {

	private String senha;

	/**
	 * Construtor da {@link Senha}
	 * 
	 * @param senha
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 */
	public Senha(String senha) throws ArgumentInvalidException {
		setSenha(senha);
	}

	/**
	 * Metodo modificador de senha
	 * 
	 * @param novaSenha
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 */
	public void setSenha(String novaSenha) throws ArgumentInvalidException {
		if (!validaSenha(novaSenha)) {
			throw new ArgumentInvalidException(Constantes.SENHA_INVALIDA.getName());
		}
		this.senha = novaSenha;
	}

	/**
	 * Metodo acessador de senha
	 * 
	 * @return String senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Metodo validador que verifica a senha
	 * 
	 * @param senha
	 *            {@link String}
	 * @return True caso a senha seja valida
	 */
	private boolean validaSenha(String senha) {
		if (senha != null && !senha.trim().isEmpty())
			return true;
		return false;
	}

	/**
	 * Metodo que verifica duas senhas.
	 * 
	 * @param senha
	 *            {@link String}
	 * @return True caso a senha seja valida
	 */
	public boolean equals(String senha) {
		if (senha == null)
			return false;
		return this.senha.equals(senha);
	}

}
