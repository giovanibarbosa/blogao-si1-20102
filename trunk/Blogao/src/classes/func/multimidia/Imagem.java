package classes.func.multimidia;

import enuns.Constantes;
import ourExceptions.ArgumentInvalidException;

/**
 * Classe que inicializa uma Imagem
 * 
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 */

public class Imagem extends Midia{
	private String descricao;
	private String dado;

	/**
	 * Construtor default do Imagem
	 */
	public Imagem() {
		setId(gerarId());
	}

	/**
	 * Construtor do objeto Imagem
	 * 
	 * @param desc
	 *            {@link String} Descricao da imagem.
	 * @param dado
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 */
	public Imagem(String desc, String dado) throws ArgumentInvalidException {
		setId(gerarId());
		setDescricao(desc);
		setDado(dado);
	}

	/**
	 * Metodo acessador de Descricao
	 * 
	 * @return String descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Metodo modificador da descricao
	 * 
	 * @param desc
	 *            {@link String}
	 */
	public void setDescricao(String desc) {
		descricao = desc;
	}

	/**
	 * Metodo acessador de dados
	 * 
	 * @return String dado
	 */
	public String getDado() {
		return dado;
	}

	/**
	 * Metodo modificador de dados
	 * 
	 * @param dado
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 *             caso o dado seja invalido
	 */
	public void setDado(String dado) throws ArgumentInvalidException {
		if (dado == null || dado.trim().isEmpty())
			throw new ArgumentInvalidException(Constantes.DADO_INVALIDO.getName());
		this.dado = dado;
	}

	private String gerarId() {
		return String.valueOf(this.hashCode());
	}

}