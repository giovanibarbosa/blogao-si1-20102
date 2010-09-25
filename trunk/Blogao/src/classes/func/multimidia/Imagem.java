package classes.func.multimidia;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.SystemColor;
import java.awt.Toolkit;
  
import ourExceptions.ArgumentInvalidException;

/**
 * Classe que manipula imagens do blog.
 * @author Tiago
 *
 */

public class Imagem {  
	private String id;
	
	public Imagem(){
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