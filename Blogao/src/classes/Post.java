package classes;

import java.io.UnsupportedEncodingException;

import ourExceptions.ArgumentInvalidException;

public class Post {
	private Texto post;
	public Post(Texto post) throws ArgumentInvalidException{
		if(validaPost(post)){
			this.post = post;
		}else{
			throw new ArgumentInvalidException("Atriburo inválido");
		}
	}
	private boolean validaPost(Texto post) {
		if(post == null || post.getCorpo().trim().equals(""))
			return true;
		else
			return true;
	}
	/**destructor do post.
	 * 
	 * @return
	 */
	//ESSE METODO DEVE INTERAGIR COM O BD.
	public boolean deleta(){
		return false;
		
	}
	
	/**
	 * Metodo que codifica a String para o padrao ISO.
	 * @param string
	 * @return
	 */
	private String codificaString(String string) {
		String retorno = "";
		try {
			retorno = new String(string.getBytes(), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
}
