package classes;

import ourExceptions.ArgumentInvalidException;

public class Comentario {

	private int id;
	private Texto corpoComentario;
	
	/**
	 * Contrutor da classe Comentario.
	 * Recebe uma string como testo do comentario.
	 * @param corpo
	 */
	public Comentario(String corpo){
		try {
			corpoComentario =  new Texto(corpo);
		} catch (ArgumentInvalidException e) {
			e.printStackTrace();
		}
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
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return o id do comnetario.
	 */
	public int getId() {
		return id;
	}
		
}
