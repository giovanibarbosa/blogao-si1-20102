package classes.func.multimidia;

import interfaces.Constantes;
import ourExceptions.ArgumentInvalidException;

/**
 * Classe que inicializa uma Video
 * 
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 */
public class Video extends Midia{
	//private String id;
	private String descricao;
	private String dado;

	/**
	 * Construtor default do Imagem
	 */
	public Video() {
		setId(gerarId());
	}

	/**
	 * Construtor do objeto Video
	 * 
	 * @param desc
	 *            {@link String} Descricao do video.
	 * @param dado
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 */
	public Video(String desc, String dado) throws ArgumentInvalidException {
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
			throw new ArgumentInvalidException(Constantes.DADO_INVALIDO);
		this.dado = dado;
	}

	/**
	 * Metodo acessador de ids do Video.
	 * 
	 * @return String idVideo
	 */
//	public String getId() {
//		return id;
//	}

	/**
	 * Metodo modificador de id da Video.
	 * 
	 * @param id
	 *            {@link String}
	 */
//	public void setId(String id) {
//		this.id = id;
//	}

	private String gerarId() {
		return String.valueOf(this.hashCode());
	}
}
