package classes.func.multimidia;

import java.util.Iterator;
import java.util.NoSuchElementException;

import interfaces.Constantes;
import ourExceptions.ArgumentInvalidException;

public class Midia{
	
	private String id;

	/**
	 * Metodo acessador de ids do audio.
	 * 
	 * @return String idAudio
	 */
	public String getId() {
		return id;
	}

	/**
	 * Metodo modificador de id do Audio.
	 * 
	 * @param id
	 *            {@link String}
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	public Midia fabricaDeMidia(String midia, String descricao, String dado) throws ArgumentInvalidException{
		if(midia.equalsIgnoreCase("imagem"))
			return new Imagem(descricao, dado);
		else if(midia.equalsIgnoreCase("video"))
			return new Video(descricao, dado);
		else if(midia.equalsIgnoreCase("audio"))
			return new Audio(descricao, dado);
		else
			throw new ArgumentInvalidException(Constantes.MIDIA_INVALIDA);

		
	}


}
