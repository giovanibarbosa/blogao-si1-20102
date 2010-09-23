package classes;

import java.io.UnsupportedEncodingException;

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
	
	public String getSenha() {
		return senha;
	}
	
	private boolean validaSenha(String senha) {
		if (senha != null && !senha.trim().isEmpty())
            return true;
        return false;
	}
	
	/**
	 * Metodo que codifica a String para o padrao ISO.
	 * @param string
	 * @return
	 */
	private String codificaString(String string) {
		String retorno = "";
		try {
			retorno = new String(string.getBytes(), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return retorno;
	}

}
