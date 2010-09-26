package classes.func.multimidia;

import interfaces.Constantes;
import ourExceptions.ArgumentInvalidException;

public class Audio {
private String id;
private String descricao;
private String dado;
	
	public Audio(String desc, String dado) throws ArgumentInvalidException{
		setId(gerarId());
		setDescricao(desc);
		setDado(dado);
	}
	
	public Audio() {
		setId(gerarId());
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String desc) {
		descricao = desc;
	}
	
	public String getDado() {
		return dado;
	}
	
	public void setDado(String dado) throws ArgumentInvalidException {
		if (dado == null || dado.trim().isEmpty())
			throw new ArgumentInvalidException(Constantes.DADO_INVALIDO);
		this.dado = dado;
	}
	
	/**
	 * Retorna o id da classe.
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Modifica o id da classe.
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * Gera o id da classe.
	 * @return
	 */
	private String gerarId() {
		return String.valueOf(this.hashCode());
	}
}
