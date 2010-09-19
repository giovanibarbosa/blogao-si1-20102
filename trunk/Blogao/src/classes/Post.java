package classes;

import ourExceptions.ArgumentInvalidException;

public class Post {
	private Texto post;
	public Post(Texto post){
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
	
}
