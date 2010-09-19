package classes.func.usuario;



import java.util.Iterator;

import ourExceptions.ArgumentInvalidException;

import classes.Blog;
import classes.Post;

public class DeletaBlog {
	private Usuario usuario;
	private Blog blog;
	
	public DeletaBlog(Usuario usuario, Blog blog){
		//subtende-se que o usuario ja existe entao n precisa validar novamente
		this.usuario = usuario;
		this.blog = blog;
	}
	
	/**
	 * Metodo que exclui todos os post de um blog.
	 * @return boolean caso tenha sido bem sucessida a operacao.
	 * @throws ArgumentInvalidException 
	 */
	public boolean deletaPosts() throws ArgumentInvalidException{
		if(blog != null){
			if(!blog.listaDePosts().isEmpty()){
				Iterator<Post> itPosts = (Iterator) blog.listaDePosts().iterator();
				while(itPosts.hasNext()){
					if(itPosts.next() != null && itPosts.next() instanceof Post){
						itPosts.next().deleta();
					}else{
						throw new ArgumentInvalidException("Post inválido.");
					}	
				}
				return true;
			}
		}	
		return false;
	
	}
	
	
	public boolean deletaBlog() throws ArgumentInvalidException{
		if(usuario != null){
			if(!usuario.listaDeBlogs().isEmpty()){
				Iterator<Blog> itBlogs = (Iterator) usuario.listaDeBlogs().iterator();
				while(itBlogs.hasNext()){
					if(itBlogs.next() != null && itBlogs.next() instanceof Blog){
						itBlogs.next().deleta();
					}else{
						throw new ArgumentInvalidException("Blog inválido.");
					}	
				}
				return true;
			}
		}	
		return false;
	}
	//como indica os testes o usuario e deslogado.
	//Sitema.logOff();
}
