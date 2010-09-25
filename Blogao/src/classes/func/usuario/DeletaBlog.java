package classes.func.usuario;



import interfaces.Constantes;

import java.io.FileNotFoundException;
import java.util.Iterator;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;

import classes.Blog;
import classes.Post;
//VER US10.
public class DeletaBlog {
	private Usuario usuario;
	
	public DeletaBlog(Usuario usuario){
		//subtende-se que o usuario ja existe entao n precisa validar novamente
		this.usuario = usuario;
	}
	
	/**
	 * Metodo que exclui todos os post de um blog.
	 * @return boolean caso tenha sido bem sucessida a operacao.
	 * @throws ArgumentInvalidException 
	 */
	public boolean deletaPosts(Blog blog) throws ArgumentInvalidException{
		if(blog != null){
			if(!blog.listaDePosts().isEmpty()){
				Iterator<Post> itPosts = (Iterator) blog.listaDePosts().iterator();
				while(itPosts.hasNext()){
					if(itPosts.next() != null && itPosts.next() instanceof Post){
						try {
							itPosts.next().deleta();
						} catch (PersistenceException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						throw new ArgumentInvalidException(Constantes.POST_INVALIDO);
					}	
				}
				return true;
			}
		}	
		return false;
	
	}
	
	/**
	 * Metodo que exclui um dado blog de um usuario.
	 * @return boolean caso tenha sido bem sucessida a operacao.
	 * @throws ArgumentInvalidException 
	 * @throws FileNotFoundException 
	 * @throws PersistenceException 
	 */
	public boolean deletaBlog(Blog blog) throws ArgumentInvalidException, FileNotFoundException, PersistenceException{
		if(usuario != null){
			if(!usuario.listaDeBlogs().isEmpty()){
				Iterator<Blog> itBlogs = (Iterator) usuario.listaDeBlogs().iterator();
				while(itBlogs.hasNext()){
					if(itBlogs.next() != null && itBlogs.next() instanceof Blog && blog.equals(itBlogs.next())){
						Blog b = itBlogs.next();
						b.deleta();
					}else{
						throw new ArgumentInvalidException(Constantes.BLOG_INVALIDO);
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
