package classes.func.multimidia;

import enuns.Constantes2;
import ourExceptions.ArgumentInvalidException;

/**
 * Classe que inicializa um Audio
 * 
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 */
public class Audio extends Midia{

	private String descricao;
	private String dado;

	/**
	 * Construtor default do audio
	 */
	public Audio() {
		setId(gerarId());
	}

	/**
	 * Construtor do objeto Audio
	 * 
	 * @param desc
	 *            {@link String} descricao do audio
	 * @param dado
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 */
	public Audio(String desc, String dado) throws ArgumentInvalidException {
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
	 *            {@link String} nova descricao.
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
	 * @throws ArgumentInvalidException
	 *             caso o dado seja invalido
	 */
	public void setDado(String dado) throws ArgumentInvalidException{
		if (dado == null || dado.trim().isEmpty())
			throw new ArgumentInvalidException(Constantes2.DADO_INVALIDO.getName());
		this.dado = dado;
	}

	private String gerarId() {
		return String.valueOf(this.hashCode());
	}
}
