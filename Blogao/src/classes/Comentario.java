package classes;

import ourExceptions.ArgumentInvalidException;

public class Comentario {

	private int id;
	private Texto corpoComentario;
	
	public Comentario(String corpo){
		try {
			corpoComentario =  new Texto(corpo);
		} catch (ArgumentInvalidException e) {
			e.printStackTrace();
		}
	}
	public Texto getCorpoComentario() {
		return corpoComentario;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
