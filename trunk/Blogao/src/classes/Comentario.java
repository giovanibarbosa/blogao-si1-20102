package classes;

import ourExceptions.ArgumentInvalidException;

public class Comentario {

	private String id;
	private Texto corpoComentario;
	
	/**
	 * Contrutor da classe Comentario.
	 * Recebe uma string como testo do comentario.
	 * @param corpo
	 */
	public Comentario(String corpo){
		try {
			corpoComentario =  new Texto(corpo);
			setId(gerarId());
		} catch (ArgumentInvalidException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gera o id da classe.
	 * @return
	 */
	private String gerarId() {
		return String.valueOf(this.hashCode());
	}
	
	/**
	 * @return o texto relativo ao comentario.
	 */
	public Texto getCorpoComentario() {
		return corpoComentario;
	}
	/**
	 * Seta o id do Comentario.
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return o id do comnetario.
	 */
	public String getId() {
		return id;
	}
	
	@Override
	/**
	 * Metodo que verifica a igualdade entre objetos.
	 */
	public boolean equals(Object objeto) {
		if (!(objeto instanceof Comentario)) {
			return false;
		}
		Comentario outra = (Comentario) objeto;
		return getId().equals(outra.getId());

	}
		
}
