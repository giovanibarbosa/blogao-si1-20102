package classes.func.multimidia;

public class Video {
private String id;
	
	public Video(){
		setId(gerarId());
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
