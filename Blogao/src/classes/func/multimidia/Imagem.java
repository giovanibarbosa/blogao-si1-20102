package classes.func.multimidia;

import interfaces.Constantes;

  
import ourExceptions.ArgumentInvalidException;

/**
 * Classe que inicializa uma Imagem
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 *
 */

public class Imagem {  
	private String id;
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
	 * @param String descricao
	 * @param String dado
	 * @throws ArgumentInvalidException
	 */
	public Imagem(String desc, String dado) throws ArgumentInvalidException {
		setId(gerarId());
		setDescricao(desc);
		setDado(dado);
	}

	/**
	 * Metodo acessador de Descricao
	 * @return String descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Metodo modificador da descricao
	 * @param String nova descricao
	 */
	public void setDescricao(String desc) {
		descricao = desc;
	}

	/**
	 * Metodo acessador de dados
	 * @return String dado
	 */
	public String getDado() {
		return dado;
	}

	
	/**
	 * Metodo modificador de dados
	 * @param String dado
	 * @throws ArgumentInvalidException caso o dado seja invalido
	 */
	public void setDado(String dado) throws ArgumentInvalidException {
		if (dado == null || dado.trim().isEmpty())
			throw new ArgumentInvalidException(Constantes.DADO_INVALIDO);
		this.dado = dado;
	}
	
	
	/**
	 * Metodo acessador de ids da imagem.
	 * @return String idImagem
	 */
	public String getId() {
		return id;
	}

	/**
	 * Metodo modificador de id da Imagem.
	 * @param String novaId
	 */
	public void setId(String id) {
		this.id = id;
	}


	private String gerarId() {
		return String.valueOf(this.hashCode());
	}
	
} 