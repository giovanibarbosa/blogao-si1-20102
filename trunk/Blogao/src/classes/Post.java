package classes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.PostsDAO;

public class Post {
	private Texto post;
	private ArrayList<Comentario> comentarios;
	private PostsDAO postDao;

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
	
	
	public ArrayList<Comentario> getComentarios() {
		return comentarios;
	}
	
	public boolean addComentario(Comentario comentario) {
		if(comentarios.contains(comentario)){
			return false;
		}else{
			comentarios.add(comentario);
			return true;
		}
	}
	
	public boolean removeComentario(Comentario comentario){
		if(comentarios.contains(comentario)){
			comentarios.remove(comentario);
			return true;
		}else{
			return false;
		}
	}
	
	public void saveData() throws PersistenceException, IOException{
		postDao.getInstance();
		postDao.atualizar(this , this);
	}
	
	
}
